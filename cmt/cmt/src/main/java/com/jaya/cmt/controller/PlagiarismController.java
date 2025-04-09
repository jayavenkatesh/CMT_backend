package com.jaya.cmt.controller;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.jaya.cmt.service.PlagiarismService;
//
//import java.io.IOException;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class PlagiarismController {
//    private final PlagiarismService plagiarismService;
//
//    public PlagiarismController(PlagiarismService plagiarismService) {
//        this.plagiarismService = plagiarismService;
//    }
//
//    @PostMapping("/checkPlagiarism")
//    public Map<String, Object> checkPlagiarism(@RequestParam("file") MultipartFile file) {
//        try {
//            String text = plagiarismService.extractText(file);
//            return plagiarismService.checkPlagiarism(text);
//        } catch (IOException e) {
//            return Map.of("error", "Failed to extract text from file");
//        }
//    }
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jaya.cmt.service.PlagiarismService;
@CrossOrigin
@RestController
@RequestMapping("/api/plagiarism")
public class PlagiarismController {

    @Autowired
    private PlagiarismService plagiarismService;

    @PostMapping("/check")
    public ResponseEntity<String> checkPlagiarism(@RequestParam("file") MultipartFile file) {
        String extractedText = plagiarismService.extractText(file);

        if ("Error extracting text from file.".equals(extractedText)) {
            return ResponseEntity.badRequest().body("Failed to process the document.");
        }

        boolean isPlagiarized = plagiarismService.checkPlagiarism(extractedText);
        
        if (isPlagiarized) {
            return ResponseEntity.ok("Plagiarism detected!");
        } else {
            return ResponseEntity.ok("No plagiarism found. The paper is unique.");
        }
    }
}

