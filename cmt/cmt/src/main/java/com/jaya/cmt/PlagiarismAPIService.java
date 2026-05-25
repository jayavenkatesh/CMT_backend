package com.jaya.cmt;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Service class for checking plagiarism using external API.
 * Handles secure API key management and URL encoding.
 */
public class PlagiarismAPIService {
    
    private static final Logger LOGGER = Logger.getLogger(PlagiarismAPIService.class.getName());
    private static final String API_KEY = System.getenv("PLAGIARISM_API_KEY");
    private static final String BASE_URL = "https://api.plagiarismchecker.com/check";

    /**
     * Checks text for plagiarism using external API.
     * @param text The text to check for plagiarism
     * @return API response as String
     * @throws IllegalStateException if API key is not set
     * @throws RestClientException if API call fails
     */
    public static String checkPlagiarism(String text) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            String errorMsg = "API key is missing. Set the PLAGIARISM_API_KEY environment variable.";
            LOGGER.severe(errorMsg);
            throw new IllegalStateException(errorMsg);
        }

        try {
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
            String apiUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("text", encodedText)
                    .queryParam("api_key", API_KEY)
                    .toUriString();

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (RestClientException e) {
            LOGGER.severe("Failed to call plagiarism API: " + e.getMessage());
            throw new RuntimeException("Plagiarism check failed", e);
        } catch (Exception e) {
            LOGGER.severe("Unexpected error during plagiarism check: " + e.getMessage());
            throw new RuntimeException("Unexpected error during plagiarism check", e);
        }
    }
}
