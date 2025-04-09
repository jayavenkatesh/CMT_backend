package com.jaya.cmt.service;
//
//import org.apache.tika.Tika;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class PlagiarismService {
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public String extractText(MultipartFile file) throws IOException {
//        Tika tika = new Tika();
//        return tika.parseToString(file);
//    }
//
//    public Map<String, Object> checkPlagiarism(String text) {
//        String url = "http://localhost:5001/checkPlagiarism";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        
//        Map<String, String> body = new HashMap<>();
//        body.put("text", text);
//        
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
//        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
//
//        return response.getBody();
//    }
//}

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Service
public class PlagiarismService {

    private final Tika tika = new Tika();
    
    // Simulated database of submitted papers (for basic plagiarism check)
    private final Set<String> submittedPapers = new HashSet<>();

    public String extractText(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {  
            return tika.parseToString(inputStream);
        } catch (IOException | TikaException e) {
            e.printStackTrace();
            return "Error extracting text from file.";
        }
    }

    // Method to check for plagiarism
    public boolean checkPlagiarism(String paperText) {
    	System.out.println(paperText);
        if (submittedPapers.contains(paperText)) {
            return true; // Plagiarized
        } else {
            submittedPapers.add(paperText);
            return false; // Unique content
        }
    }
}

