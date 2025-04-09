//package com.jaya.cmt;
//
//import org.springframework.web.client.RestTemplate;
//
//public class PlagiarismAPIService {
//    public static String checkPlagiarism(String text) {
//        String apiUrl = "https://api.plagiarismchecker.com/check?text=" + text + "&api_key=ND2w3LDNR9d1HxHymVEur9-zAyLbc9Cp";
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(apiUrl, String.class);
//    }
//}



package com.jaya.cmt;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PlagiarismAPIService {

    private static final String API_KEY = System.getenv("ND2w3LDNR9d1HxHymVEur9-zAyLbc9Cp"); // Load from environment variable
    private static final String BASE_URL = "https://api.plagiarismchecker.com/check";

    public static String checkPlagiarism(String text) {
        if (API_KEY == null) {
            throw new IllegalStateException("API key is missing. Set the PLAGIARISM_API_KEY environment variable.");
        }

        // URL Encode the text
        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);

        // Construct the URL with query parameters
        String apiUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("text", encodedText)
                .queryParam("api_key", API_KEY)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }
}

