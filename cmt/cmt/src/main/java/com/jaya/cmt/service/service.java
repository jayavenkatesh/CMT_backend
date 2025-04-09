package com.jaya.cmt.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;
import java.util.Set;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jaya.cmt.Collaborators;
import com.jaya.cmt.Paper;
import com.jaya.cmt.repository.PaperRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Image;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.chart.renderer.category.BarRenderer;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class service {

	@Autowired
    private emailService emailService;
    @Autowired
    private PaperRepository repository;


//	public ResponseEntity<String> uploadpaper(String title, String abstractContent, List<String> collaborators,
//			MultipartFile file,String userEmail,Integer confId) {
//        String uploadDir = "c:/uploads/";
//
//        File dir = new File(uploadDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        String filePath = uploadDir + file.getOriginalFilename();
//        try {
//            file.transferTo(new File(filePath)); 
//        } catch (IOException e) {
//            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        Paper paper = new Paper();
//        paper.setTitle(title);
//        paper.setAbstractContent(abstractContent);
//        paper.setCollaborators(collaborators);
//        paper.setFilePath(filePath); 
//        paper.setUserMail(userEmail);
//        paper.setConfId(confId);
//        repository.save(paper); 
//
//        String email = userEmail;
//        emailService.sendEmail(email, "Paper Submission Confirmation", "Hello,\r\n"
//        		+ "\r\n"
//        		+ "The following submission has been created.\r\n"
//        		+ "\r\n"
//        		
//        		+ "\r\n"
//        		+ "Paper Title: "+title+"\r\n"
//        		+ "\r\n"
//        		+ "Abstract:"+abstractContent+"\r\n"
//        		
//        		+ "\r\n"
//        		
//        		
//        		+ "\r\n"
//        		+ "Thanks,\r\n"
//        		+ "CMT team.");
//        return new ResponseEntity<>("Paper uploaded successfully", HttpStatus.OK);
//	}
    
    
    
    
    
    
    
    
//    public ResponseEntity<String> uploadpaper(String title, String abstractContent, List<String> collaborators,
//            MultipartFile file, String userEmail, Integer confId) {
//    	
//		// Plagiarism API URL & Key
//		//String apiUrl = "https://api.plagiarismcheck.org/v1/text";
//		String apiUrl = "https://plagiarismcheck.org/api/v1/text";
//		String apiKey = "ND2w3LDNR9d1HxHymVEur9-zAyLbc9Cp";
//		
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.set("API-Key", apiKey);
//		
//		Map<String, String> requestBody = Map.of("text", abstractContent);
//		HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
//		
//		ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, request, Map.class);
//		
//		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//			Double plagiarismPercentage = (Double) response.getBody().get("plagiarism");
//			if (plagiarismPercentage > 0.0) {
//				return new ResponseEntity<>("Plagiarism detected: " + plagiarismPercentage + "%", HttpStatus.BAD_REQUEST);
//			}
//		}
//
//		// File upload logic
//		String uploadDir = "c:/uploads/";
//		File dir = new File(uploadDir);
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//		
//		String filePath = uploadDir + file.getOriginalFilename();
//		try {
//			file.transferTo(new File(filePath));
//		} catch (IOException e) {
//			return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		// Save paper details in database
//		Paper paper = new Paper();
//		paper.setTitle(title);
//		paper.setAbstractContent(abstractContent);
//		paper.setCollaborators(collaborators);
//		paper.setFilePath(filePath);
//		paper.setUserMail(userEmail);
//		paper.setConfId(confId);
//		repository.save(paper);
//		
//		emailService.sendEmail(userEmail, "Paper Submission Confirmation", "Your paper has been submitted successfully.");
//		return new ResponseEntity<>("Paper uploaded successfully", HttpStatus.OK);
//	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//    public ResponseEntity<String> uploadpaper(String title, String abstractContent, List<String> collaborators,
//                                              MultipartFile file, String userEmail, Integer confId) {
//        // Plagiarism API URL
//        String plagiarismApiUrl = "http://localhost:5000/check_plagiarism";
//
//        // Prepare request payload
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("text", abstractContent);
//
//        // Call Plagiarism API
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<Map> response = restTemplate.exchange(plagiarismApiUrl, HttpMethod.POST, request, Map.class);
//        
//        // Extract response
//        Boolean isPlagiarized = (Boolean) response.getBody().get("plagiarized");
//        Double similarityScore = (Double) response.getBody().get("similarity_score");
//
//        if (Boolean.TRUE.equals(isPlagiarized)) {
//            return new ResponseEntity<>("Plagiarism detected! Similarity Score: " + similarityScore, HttpStatus.BAD_REQUEST);
//        }
//
//        // File upload logic
//        String uploadDir = "c:/uploads/";
//        File dir = new File(uploadDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        String filePath = uploadDir + file.getOriginalFilename();
//        try {
//            file.transferTo(new File(filePath));
//        } catch (IOException e) {
//            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        // Save paper details in the database
//        Paper paper = new Paper();
//        paper.setTitle(title);
//        paper.setAbstractContent(abstractContent);
//        paper.setCollaborators(collaborators);
//        paper.setFilePath(filePath);
//        paper.setUserMail(userEmail);
//        paper.setConfId(confId);
//        repository.save(paper);
//
//        // Send confirmation email
//        String email = userEmail;
//        emailService.sendEmail(email, "Paper Submission Confirmation",
//                "Hello,\n\nThe following submission has been created.\n\n" +
//                        "Paper Title: " + title + "\n\n" +
//                        "Abstract: " + abstractContent + "\n\n" +
//                        "Thanks,\nCMT team.");
//
//        return new ResponseEntity<>("Paper uploaded successfully", HttpStatus.OK);
//    }

    
    
    
    
    
    //plagarizammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
    
    
    
    
    
    
    
    
    
    
    
//    public ResponseEntity<String> uploadpaper(String title, String abstractContent, List<String> collaborators,
//            MultipartFile file, String userEmail, Integer confId) {
//        String uploadDir = "c:/uploads/";
//
//        File dir = new File(uploadDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        String filePath = uploadDir + file.getOriginalFilename();
//        try {
//            file.transferTo(new File(filePath)); 
//        } catch (IOException e) {
//            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        
//        // Perform plagiarism check before saving
//        boolean isPlagiarized = performPlagiarismCheck(filePath, abstractContent);
//        
//        if (isPlagiarized) {
//            // Delete the uploaded file since it contains plagiarism
//            new File(filePath).delete();
//            return new ResponseEntity<>("Plagiarism found in the submitted paper", HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        Paper paper = new Paper();
//        paper.setTitle(title);
//        paper.setAbstractContent(abstractContent);
//        paper.setCollaborators(collaborators);
//        paper.setFilePath(filePath); 
//        paper.setUserMail(userEmail);
//        paper.setConfId(confId);
//        repository.save(paper); 
//
//        String email = userEmail;
//        emailService.sendEmail(email, "Paper Submission Confirmation", "Hello,\r\n"
//                + "\r\n"
//                + "The following submission has been created.\r\n"
//                + "\r\n"
//                
//                + "\r\n"
//                + "Paper Title: "+title+"\r\n"
//                + "\r\n"
//                + "Abstract:"+abstractContent+"\r\n"
//                
//                + "\r\n"
//                
//                
//                + "\r\n"
//                + "Thanks,\r\n"
//                + "CMT team.");
//        return new ResponseEntity<>("Paper uploaded successfully", HttpStatus.OK);
//    }
    
    //plazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
    
    
    public ResponseEntity<String> uploadpaper(String title, String abstractContent, List<String> collaborators,
            MultipartFile file, String userEmail, Integer confId) {
        String uploadDir = "c:/uploads/";

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = uploadDir + file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath)); 
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        // Perform plagiarism check before saving
        PlagiarismResult result = performPlagiarismChecks(filePath, abstractContent);
        
        if (result.isPlagiarized()) {
            // Generate and download plagiarism report as PDF
            String reportPath = generatePlagiarismReport(result, title, userEmail, file.getOriginalFilename());
            
            // Delete the uploaded file since it contains plagiarism
            new File(filePath).delete();
            
            return new ResponseEntity<>("Plagiarism found in the submitted paper. Report downloaded to: " + reportPath, 
                                       HttpStatus.NOT_ACCEPTABLE);
        }

        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setAbstractContent(abstractContent);
        paper.setCollaborators(collaborators);
        paper.setFilePath(filePath); 
        paper.setUserMail(userEmail);
        paper.setConfId(confId);
        repository.save(paper); 

        String email = userEmail;
        emailService.sendEmail(email, "Paper Submission Confirmation", "Hello,\r\n"
                + "\r\n"
                + "The following submission has been created.\r\n"
                + "\r\n"
                
                + "\r\n"
                + "Paper Title: "+title+"\r\n"
                + "\r\n"
                + "Abstract:"+abstractContent+"\r\n"
                
                + "\r\n"
                
                
                + "\r\n"
                + "Thanks,\r\n"
                + "CMT team.");
        return new ResponseEntity<>("Paper uploaded successfully", HttpStatus.OK);
    }

    /**
     * Performs plagiarism check on the uploaded paper
     * @param filePath Path to the uploaded file
     * @param abstractContent Abstract of the paper
     * @return PlagiarismResult containing detection status and details
     */
    @Async
    private PlagiarismResult performPlagiarismChecks(String filePath, String abstractContent) {
        PlagiarismResult result = new PlagiarismResult();
        result.setPlagiarized(false);
        
        try {
            // Read file content
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            
            // 1. Check against database of existing papers
            List<Paper> existingPapers = repository.findAll();
            
            // 2. Compare with existing papers
            for (Paper existingPaper : existingPapers) {
                double similarityScore = calculateSimilarity(fileContent, existingPaper.getFilePath());
                double abstractSimilarityScore = calculateSimilarity(abstractContent, existingPaper.getAbstractContent());
                
                // If similarity exceeds threshold, it's considered plagiarism
                if (similarityScore > 0.7 || abstractSimilarityScore > 0.8) {
                    result.setPlagiarized(true);
                    result.setMatchedPaper(existingPaper);
                    result.setSimilarityScore(Math.max(similarityScore, abstractSimilarityScore));
                    break;
                }
            }
            
            // 3. Optionally, integrate with external plagiarism detection API
            // boolean externalCheckResult = callExternalPlagiarismAPI(fileContent);
            // if (externalCheckResult) {
            //     result.setPlagiarized(true);
            //     result.setExternalDetection(true);
            // }
            
        } catch (IOException e) {
            e.printStackTrace();
            // In case of error, you might want to err on the side of caution
            // result.setPlagiarized(true);
            // result.setErrorOccurred(true);
        }
        
        return result;
    }
    
    /**
     * Generates a PDF report for plagiarism detection
     * @param result The plagiarism check result
     * @param title The title of the submitted paper
     * @param userEmail The email of the user who submitted the paper
     * @param fileName The name of the submitted file
     * @return Path to the generated PDF report
     */
    
    
    
    
    //changeddddddddddddddddddddddddddddddddddddddddddddddddd
    
    
    
    
    private String generatePlagiarismReports(PlagiarismResult result, String title, String userEmail, String fileName) {
        String reportsDir = "c:/plagiarism_reports/";
        File dir = new File(reportsDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        // Create a unique filename based on timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFileName = "plagiarism_report_" + timestamp + ".pdf";
        String reportPath = reportsDir + reportFileName;
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(reportPath));
            document.open();
            
            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titlePara = new Paragraph("Plagiarism Detection Report", titleFont);
            titlePara.setAlignment(Element.ALIGN_CENTER);
            document.add(titlePara);
            document.add(new Paragraph(" ")); // Add spacing
            
            // Add details
            document.add(new Paragraph("Report Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            document.add(new Paragraph("Paper Title: " + title));
            document.add(new Paragraph("Submitted By: " + userEmail));
            document.add(new Paragraph("Filename: " + fileName));
            document.add(new Paragraph(" ")); // Add spacing
            
            // Add plagiarism details
            Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            document.add(new Paragraph("Plagiarism Detection Results", subTitleFont));
            document.add(new Paragraph("Plagiarism Detected: Yes"));
            document.add(new Paragraph("Similarity Score: " + (result.getSimilarityScore() * 100) + "%"));
            
            if (result.getMatchedPaper() != null) {
                document.add(new Paragraph(" ")); // Add spacing
                document.add(new Paragraph("Matched with existing paper:", subTitleFont));
                document.add(new Paragraph("Title: " + result.getMatchedPaper().getTitle()));
                document.add(new Paragraph("Author: " + result.getMatchedPaper().getUserMail()));
            }
            
            document.close();
            
            System.out.println("Plagiarism report generated at: " + reportPath);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to generate report";
        }
        
        return reportPath;
    }
    
    /**
     * Helper class to store plagiarism check results
     */
    
    
    //today----------------------------------------------------------------
    
    
    
    
//    private class PlagiarismResult {
//        private boolean plagiarized;
//        private Paper matchedPaper;
//        private double similarityScore;
//        private boolean externalDetection;
//        private boolean errorOccurred;
//        
//        public boolean isPlagiarized() {
//            return plagiarized;
//        }
//        
//        public void setPlagiarized(boolean plagiarized) {
//            this.plagiarized = plagiarized;
//        }
//        
//        public Paper getMatchedPaper() {
//            return matchedPaper;
//        }
//        
//        public void setMatchedPaper(Paper matchedPaper) {
//            this.matchedPaper = matchedPaper;
//        }
//        
//        public double getSimilarityScore() {
//            return similarityScore;
//        }
//        
//        public void setSimilarityScore(double similarityScore) {
//            this.similarityScore = similarityScore;
//        }
//        
//        public boolean isExternalDetection() {
//            return externalDetection;
//        }
//        
//        public void setExternalDetection(boolean externalDetection) {
//            this.externalDetection = externalDetection;
//        }
//        
//        public boolean isErrorOccurred() {
//            return errorOccurred;
//        }
//        
//        public void setErrorOccurred(boolean errorOccurred) {
//            this.errorOccurred = errorOccurred;
//        }
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //------------------endddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd

    /**
     * Performs plagiarism check on the uploaded paper
     * @param filePath Path to the uploaded file
     * @param abstractContent Abstract of the paper
     * @return true if plagiarism is detected, false otherwise
     */
    
    @Async
    private boolean performPlagiarismCheck(String filePath, String abstractContent) {
        // Implement your plagiarism detection logic here
        // This is a placeholder for your actual implementation
        
        try {
            // Read file content
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            
            // Example implementation:
            // 1. Check against database of existing papers
            List<Paper> existingPapers = repository.findAll();
            
            // 2. Compare with existing papers
            for (Paper existingPaper : existingPapers) {
                double similarityScore = calculateSimilarity(fileContent, existingPaper.getFilePath());
                double abstractSimilarityScore = calculateSimilarity(abstractContent, existingPaper.getAbstractContent());
                
                // If similarity exceeds threshold, it's considered plagiarism
                if (similarityScore > 0.7 || abstractSimilarityScore > 0.8) {
                    return true;
                }
            }
            
            // 3. Optionally, integrate with external plagiarism detection API
            // boolean externalCheckResult = callExternalPlagiarismAPI(fileContent);
            // if (externalCheckResult) {
            //     return true;
            // }
            
        } catch (IOException e) {
            e.printStackTrace();
            // In case of error, you might want to err on the side of caution
            // return true; // Uncomment if you want to reject submissions when check fails
        }
        
        return false; // No plagiarism detected
    }

    /**
     * Calculate similarity between two texts
     * @param text1 First text
     * @param text2 Second text or file path to second text
     * @return Similarity score between 0 and 1
     */
    private double calculateSimilarity(String text1, String text2) {
        // If text2 is a file path, read its content
        if (text2.startsWith("c:/uploads/")) {
            try {
                text2 = new String(Files.readAllBytes(Paths.get(text2)));
            } catch (IOException e) {
                e.printStackTrace();
                return 0.0;
            }
        }
        
        // Simple implementation using Jaccard similarity for demonstration
        // In a real system, you would use more sophisticated algorithms
        
        // Convert texts to lowercase and split into words
        Set<String> words1 = new HashSet<>(Arrays.asList(text1.toLowerCase().split("\\W+")));
        Set<String> words2 = new HashSet<>(Arrays.asList(text2.toLowerCase().split("\\W+")));
        
        // Find intersection and union
        Set<String> intersection = new HashSet<>(words1);
        intersection.retainAll(words2);
        
        Set<String> union = new HashSet<>(words1);
        union.addAll(words2);
        
        // Calculate Jaccard similarity
        return (double) intersection.size() / union.size();
    }
    
    
    
    


	public ResponseEntity<String> updatePaper(Integer id, String title, String abstractContent,
			List<String> collaborators, MultipartFile file,String userEmail,Integer confId) {
		
		Optional<Paper> optionalPaper = repository.findById(id);
		
		if (!optionalPaper.isPresent()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }

        Paper paper = optionalPaper.get();
        paper.setTitle(title);
        paper.setAbstractContent(abstractContent);
        paper.setCollaborators(collaborators);

        if (file != null && !file.isEmpty()) {
            String uploadDir = "uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filePath = uploadDir + file.getOriginalFilename();
            try {
                file.transferTo(new File(filePath));
                paper.setFilePath(filePath);
                
            } catch (IOException e) {
                return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            PlagiarismResult result = performPlagiarismChecks(filePath, abstractContent);
            
            if (result.isPlagiarized()) {
                // Generate and download plagiarism report as PDF
                String reportPath = generatePlagiarismReport(result, title, userEmail, file.getOriginalFilename());
                
                // Delete the uploaded file since it contains plagiarism
                new File(filePath).delete();
                
                return new ResponseEntity<>("Plagiarism found in the submitted paper. Report downloaded to: " + reportPath, 
                                           HttpStatus.NOT_ACCEPTABLE);
            }
            
            
//            boolean isPlagiarized = performPlagiarismCheck(filePath, abstractContent);
//            
//            if (isPlagiarized) {
//                // Delete the uploaded file since it contains plagiarism
//                new File(filePath).delete();
//                return new ResponseEntity<>("Plagiarism found in the submitted paper", HttpStatus.CONFLICT);
//            }
        }
        repository.save(paper);
        String email = userEmail;
        emailService.sendEmail(email, "Paper Update Confirmation",  "Hello,\r\n"
        		+ "\r\n"
        		+ "The following submission has been created.\r\n"
        		+ "\r\n"
        		
        		+ "\r\n"
        		+ "Paper Title: "+title+"\r\n"
        		+ "\r\n"
        		+ "Abstract:"+abstractContent+"\r\n"
        		
        		+ "\r\n"
        		
        		
        		+ "\r\n"
        		+ "Thanks,\r\n"
        		+ "CMT team.");
        return new ResponseEntity<>("Paper updated successfully", HttpStatus.OK);
    }


	public ResponseEntity<Paper> getPaperById(Integer id) {
		Optional<Paper> paper = repository.findById(id);

        if (!paper.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(paper.get(), HttpStatus.OK);
	}


	public ResponseEntity<String> deletePaper(Integer id) {
		Optional<Paper> paper = repository.findById(id);

        if (!paper.isPresent()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }

        
        repository.deleteById(id);
        String email = getUserEmailById(id);
        emailService.sendEmail(email, "Paper Deletion Confirmation", "Your paper has been deleted successfully.");
        return new ResponseEntity<>("Paper deleted successfully", HttpStatus.OK);
	}


	public ResponseEntity<Paper> validPaper(String userEmail, Integer confId) {
		Optional<Paper> paper = repository.findByUserEmailAndConfId(userEmail, confId);
        
        if (paper.isPresent()) {
            return new ResponseEntity<>(paper.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	private String getUserEmailById(Integer userId) {
        // Fetch the user's email from the database based on userId
        return "user@example.com"; // Replace with actual email fetching logic
    }


	public List<Paper> paperByMail(String mail) {
		return repository.findByUserMail(mail);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//now testing-------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	public String generatePlagiarismReport(PlagiarismResult result, String title, String userEmail, String fileName) {
	    String reportsDir = "c:/plagiarism_reports/";
	    File dir = new File(reportsDir);
	    if (!dir.exists()) {
	        dir.mkdirs();
	    }
	    
	    // Create a unique filename based on timestamp
	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String reportFileName = "plagiarism_report_" + timestamp + ".pdf";
	    String reportPath = reportsDir + reportFileName;
	    
	    try {
	        Document document = new Document();
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(reportPath));
	        document.open();
	        
	        // Add title
	        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
	        Paragraph titlePara = new Paragraph("Plagiarism Detection Report", titleFont);
	        titlePara.setAlignment(Element.ALIGN_CENTER);
	        document.add(titlePara);
	        document.add(new Paragraph(" ")); // Add spacing
	        
	        // Add details
	        document.add(new Paragraph("Report Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
	        document.add(new Paragraph("Paper Title: " + title));
	        document.add(new Paragraph("Submitted By: " + userEmail));
	        document.add(new Paragraph("Filename: " + fileName));
	        document.add(new Paragraph("Overall Similarity Score: " + String.format("%.2f", result.getSimilarityScore() * 100) + "%"));
	        document.add(new Paragraph(" ")); // Add spacing
	        
	        // Add comparison table
	        if (result.getMatchedPaper() != null) {
	            Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
	            document.add(new Paragraph("Comparison with Existing Paper", subTitleFont));
	            document.add(new Paragraph(" ")); // Add spacing
	            
	            // Create comparison table
	            PdfPTable table = new PdfPTable(3);
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10f);
	            table.setSpacingAfter(10f);
	            
	            // Set column widths
	            float[] columnWidths = {2f, 4f, 4f};
	            table.setWidths(columnWidths);
	            
	            // Add table headers
	            Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
	            PdfPCell cell1 = new PdfPCell(new Phrase("Property", tableHeaderFont));
	            PdfPCell cell2 = new PdfPCell(new Phrase("Current Submission", tableHeaderFont));
	            PdfPCell cell3 = new PdfPCell(new Phrase("Matched Paper", tableHeaderFont));
	            
	            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	            
	            cell1.setPadding(5);
	            cell2.setPadding(5);
	            cell3.setPadding(5);
	            
	            table.addCell(cell1);
	            table.addCell(cell2);
	            table.addCell(cell3);
	            
	            // Add table rows
	            // Title comparison
	            table.addCell(new PdfPCell(new Phrase("Title")));
	            table.addCell(new PdfPCell(new Phrase(title)));
	            table.addCell(new PdfPCell(new Phrase(result.getMatchedPaper().getTitle())));
	            
	            // Author comparison
	            table.addCell(new PdfPCell(new Phrase("Author")));
	            table.addCell(new PdfPCell(new Phrase(userEmail)));
	            table.addCell(new PdfPCell(new Phrase(result.getMatchedPaper().getUserMail())));
	            
	            // Abstract comparison
	            String currentAbstract = result.getCurrentAbstract() != null ? result.getCurrentAbstract() : "Not available";
	            String matchedAbstract = result.getMatchedPaper().getAbstractContent() != null ? 
	                    result.getMatchedPaper().getAbstractContent() : "Not available";
	            
	            table.addCell(new PdfPCell(new Phrase("Abstract")));
	            PdfPCell abstractCell1 = new PdfPCell(new Phrase(currentAbstract));
	            PdfPCell abstractCell2 = new PdfPCell(new Phrase(matchedAbstract));
	            abstractCell1.setMinimumHeight(80f);
	            abstractCell2.setMinimumHeight(80f);
	            table.addCell(abstractCell1);
	            table.addCell(abstractCell2);
	            
	            document.add(table);
	            document.add(new Paragraph(" ")); // Add spacing
	            
	            // Add similarity metrics section
	            document.add(new Paragraph("Similarity Metrics", subTitleFont));
	            document.add(new Paragraph(" ")); // Add spacing
	            
	            // Create similarity metrics table
	            PdfPTable metricsTable = new PdfPTable(2);
	            metricsTable.setWidthPercentage(70);
	            metricsTable.setHorizontalAlignment(Element.ALIGN_CENTER);
	            metricsTable.setSpacingBefore(5f);
	            metricsTable.setSpacingAfter(15f);
	            
	            // Add metrics rows
	            metricsTable.addCell(new PdfPCell(new Phrase("Overall Content Similarity")));
	            metricsTable.addCell(new PdfPCell(new Phrase(String.format("%.2f", result.getContentSimilarityScore() * 100) + "%")));
	            
	            metricsTable.addCell(new PdfPCell(new Phrase("Abstract Similarity")));
	            metricsTable.addCell(new PdfPCell(new Phrase(String.format("%.2f", result.getAbstractSimilarityScore() * 100) + "%")));
	            
	            metricsTable.addCell(new PdfPCell(new Phrase("Structure Similarity")));
	            metricsTable.addCell(new PdfPCell(new Phrase(String.format("%.2f", result.getStructureSimilarityScore() * 100) + "%")));
	            
	            document.add(metricsTable);
	            
	            // Add similarity graph
	            document.add(createSimilarityChart(writer, result));
	            document.add(new Paragraph(" ")); // Add spacing
	            
	            // Add similar sections details if available
	            if (result.getSimilarSections() != null && !result.getSimilarSections().isEmpty()) {
	                document.add(new Paragraph("Similar Content Sections", subTitleFont));
	                document.add(new Paragraph(" ")); // Add spacing
	                
	                for (SimilarSection section : result.getSimilarSections()) {
	                    document.add(new Paragraph("Section: " + section.getSectionName()));
	                    document.add(new Paragraph("Similarity Score: " + String.format("%.2f", section.getSimilarityScore() * 100) + "%"));
	                    
	                    // Create comparison table for this section
	                    PdfPTable sectionTable = new PdfPTable(2);
	                    sectionTable.setWidthPercentage(100);
	                    sectionTable.setSpacingBefore(5f);
	                    sectionTable.setSpacingAfter(10f);
	                    
	                    // Set table headers
	                    PdfPCell secCell1 = new PdfPCell(new Phrase("Current Submission"));
	                    PdfPCell secCell2 = new PdfPCell(new Phrase("Matched Paper"));
	                    secCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	                    secCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	                    sectionTable.addCell(secCell1);
	                    sectionTable.addCell(secCell2);
	                    
	                    // Add content cells
	                    PdfPCell contentCell1 = new PdfPCell(new Phrase(section.getCurrentContent()));
	                    PdfPCell contentCell2 = new PdfPCell(new Phrase(section.getMatchedContent()));
	                    contentCell1.setMinimumHeight(100f);
	                    contentCell2.setMinimumHeight(100f);
	                    sectionTable.addCell(contentCell1);
	                    sectionTable.addCell(contentCell2);
	                    
	                    document.add(sectionTable);
	                    document.add(new Paragraph(" ")); // Add spacing
	                }
	            }
	        }
	        
	        document.close();
	        System.out.println("Enhanced plagiarism report generated at: " + reportPath);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Failed to generate report";
	    }
	    
	    return reportPath;
	}

	/**
	 * Creates a bar chart showing similarity scores for different components
	 * @param writer The PdfWriter instance
	 * @param result The plagiarism check result
	 * @return An Image element containing the chart
	 */
	private Image createSimilarityChart(PdfWriter writer, PlagiarismResult result) throws Exception {
	    // Create the chart
	    JFreeChart chart = createBarChart(result);
	    
	    // Convert the chart to an image
	    int width = 500;
	    int height = 300;
	    BufferedImage bufferedImage = chart.createBufferedImage(width, height);
	    
	    // Convert to iText Image
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(bufferedImage, "png", baos);
	    Image chartImage = Image.getInstance(baos.toByteArray());
	    
	    // Set image properties
	    chartImage.setAlignment(Element.ALIGN_CENTER);
	    chartImage.scalePercent(75);
	    
	    return chartImage;
	}

	/**
	 * Creates a JFreeChart bar chart with similarity metrics
	 * @param result The plagiarism check result
	 * @return A JFreeChart instance
	 */
	private JFreeChart createBarChart(PlagiarismResult result) {
	    // Create dataset
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    // Add data points
	    dataset.addValue(result.getContentSimilarityScore() * 100, "Similarity", "Content");
	    dataset.addValue(result.getAbstractSimilarityScore() * 100, "Similarity", "Abstract");
	    dataset.addValue(result.getStructureSimilarityScore() * 100, "Similarity", "Structure");
	    dataset.addValue(result.getSimilarityScore() * 100, "Similarity", "Overall");
	    
	    // Create the chart
	    JFreeChart chart = ChartFactory.createBarChart(
	            "Similarity Comparison",       // chart title
	            "Components",                  // domain axis label
	            "Similarity (%)",              // range axis label
	            dataset,                       // data
	            PlotOrientation.VERTICAL,      // orientation
	            false,                         // include legend
	            true,                          // tooltips
	            false                          // URLs
	    );
	    
	    // Customize the chart
	    CategoryPlot plot = chart.getCategoryPlot();
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    
	    // Set bar colors based on severity
	    for (int i = 0; i < dataset.getColumnCount(); i++) {
	        Number value = dataset.getValue(0, i);
	        if (value.doubleValue() >= 80) {
	            renderer.setSeriesPaint(0, Color.RED);
	        } else if (value.doubleValue() >= 60) {
	            renderer.setSeriesPaint(0, Color.ORANGE);
	        } else {
	            renderer.setSeriesPaint(0, Color.GREEN);
	        }
	    }
	    
	    // Set the range axis to go from 0 to 100
	    ValueAxis rangeAxis = plot.getRangeAxis();
	    rangeAxis.setRange(0, 100);
	    
	    return chart;
	}

	/**
	 * Class to store information about similar sections between papers
	 */
	private class SimilarSection {
	    private String sectionName;
	    private String currentContent;
	    private String matchedContent;
	    private double similarityScore;
	    
	    // Getters and setters
	    public String getSectionName() {
	        return sectionName;
	    }
	    
	    public void setSectionName(String sectionName) {
	        this.sectionName = sectionName;
	    }
	    
	    public String getCurrentContent() {
	        return currentContent;
	    }
	    
	    public void setCurrentContent(String currentContent) {
	        this.currentContent = currentContent;
	    }
	    
	    public String getMatchedContent() {
	        return matchedContent;
	    }
	    
	    public void setMatchedContent(String matchedContent) {
	        this.matchedContent = matchedContent;
	    }
	    
	    public double getSimilarityScore() {
	        return similarityScore;
	    }
	    
	    public void setSimilarityScore(double similarityScore) {
	        this.similarityScore = similarityScore;
	    }
	}

	/**
	 * Enhanced class to store plagiarism check results
	 */
	private class PlagiarismResult {
	    private boolean plagiarized;
	    private Paper matchedPaper;
	    private double similarityScore;
	    private double contentSimilarityScore;
	    private double abstractSimilarityScore;
	    private double structureSimilarityScore;
	    private boolean externalDetection;
	    private boolean errorOccurred;
	    private String currentAbstract;
	    private List<SimilarSection> similarSections;
	    
	    public PlagiarismResult() {
	        // Initialize with default values
	        this.similarityScore = 0.0;
	        this.contentSimilarityScore = 0.0;
	        this.abstractSimilarityScore = 0.0;
	        this.structureSimilarityScore = 0.0;
	        this.similarSections = new ArrayList<>();
	    }
	    
	    // Getters and setters
	    public boolean isPlagiarized() {
	        return plagiarized;
	    }
	    
	    public void setPlagiarized(boolean plagiarized) {
	        this.plagiarized = plagiarized;
	    }
	    
	    public Paper getMatchedPaper() {
	        return matchedPaper;
	    }
	    
	    public void setMatchedPaper(Paper matchedPaper) {
	        this.matchedPaper = matchedPaper;
	    }
	    
	    public double getSimilarityScore() {
	        return similarityScore;
	    }
	    
	    public void setSimilarityScore(double similarityScore) {
	        this.similarityScore = similarityScore;
	    }
	    
	    public double getContentSimilarityScore() {
	        return contentSimilarityScore;
	    }
	    
	    public void setContentSimilarityScore(double contentSimilarityScore) {
	        this.contentSimilarityScore = contentSimilarityScore;
	    }
	    
	    public double getAbstractSimilarityScore() {
	        return abstractSimilarityScore;
	    }
	    
	    public void setAbstractSimilarityScore(double abstractSimilarityScore) {
	        this.abstractSimilarityScore = abstractSimilarityScore;
	    }
	    
	    public double getStructureSimilarityScore() {
	        return structureSimilarityScore;
	    }
	    
	    public void setStructureSimilarityScore(double structureSimilarityScore) {
	        this.structureSimilarityScore = structureSimilarityScore;
	    }
	    
	    public boolean isExternalDetection() {
	        return externalDetection;
	    }
	    
	    public void setExternalDetection(boolean externalDetection) {
	        this.externalDetection = externalDetection;
	    }
	    
	    public boolean isErrorOccurred() {
	        return errorOccurred;
	    }
	    
	    public void setErrorOccurred(boolean errorOccurred) {
	        this.errorOccurred = errorOccurred;
	    }
	    
	    public String getCurrentAbstract() {
	        return currentAbstract;
	    }
	    
	    public void setCurrentAbstract(String currentAbstract) {
	        this.currentAbstract = currentAbstract;
	    }
	    
	    public List<SimilarSection> getSimilarSections() {
	        return similarSections;
	    }
	    
	    public void setSimilarSections(List<SimilarSection> similarSections) {
	        this.similarSections = similarSections;
	    }
	    
	    public void addSimilarSection(SimilarSection section) {
	        this.similarSections.add(section);
	    }
	}
}
