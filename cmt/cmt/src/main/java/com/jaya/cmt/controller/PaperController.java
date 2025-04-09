package com.jaya.cmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jaya.cmt.Collaborators;
import com.jaya.cmt.Paper;
import com.jaya.cmt.user;
import com.jaya.cmt.repository.userRepository;
import com.jaya.cmt.service.service;
import com.jaya.cmt.service.userService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
//@RequestMapping("/VITCC/Paper")
public class PaperController {

    @Autowired
    private service serv;
    @Autowired
    private userService userService;
    
    @GetMapping("/")
    public String mine() {
    	return "Hello world";
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paper> getPaperById(@PathVariable Integer id) {
        return serv.getPaperById(id);
    }
//    @PostMapping("/yep")
//    public ResponseEntity<String> yep(
//            @RequestParam("title") String title,
//            @RequestParam("abstractContent") String abstractContent,
//            @RequestParam(value = "collaborators", required = false) List<String> collaborators,
//            //@ModelAttribute(value = "collaborators") List<Collaborators> collaborators,
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("userEmail") String userEmail,
//            @RequestParam("confId") Integer confId) {
//
//        return new ResponseEntity<>(" good",HttpStatus.OK);
//    }
    
    @PostMapping("/Submission")
    public ResponseEntity<String> uploadPaper(
            @RequestParam("title") String title,
            @RequestParam("abstractContent") String abstractContent,
            @RequestParam(value = "collaborators", required = false) List<String> collaborators,
            //@ModelAttribute(value = "collaborators") List<Collaborators> collaborators,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userEmail") String userEmail,
            @RequestParam("confId") Integer confId) {

        return serv.uploadpaper(title,abstractContent,collaborators,file,userEmail,confId);
    }
    
    @GetMapping("/Submission")
    public ResponseEntity<Paper> uploadPaper(
            @RequestParam("userEmail") String userEmail,
            @RequestParam("conferenceId") Integer conferenceId) {

        return serv.validPaper(userEmail,conferenceId);
    }
    
    @PutMapping("/updatepaper/{id}")
    public ResponseEntity<String> updatePaper(
            @PathVariable Integer id,
            @RequestParam("title") String title,
            @RequestParam("abstractContent") String abstractContent,
            @RequestParam(value = "collaborators", required = false) List<String> collaborators,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("userEmail") String userEmail,
            @RequestParam("confId") Integer confId) {

        return serv.updatePaper(id, title, abstractContent, collaborators, file,userEmail,confId);
    }
    
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deletePaper(@PathVariable Integer id) {
        return serv.deletePaper(id);
    }
    @GetMapping("/getUserByMail")
    public Optional<user> getUserByMail(@RequestParam String mail) {
    	return userService.userByMail(mail);
    }
    @GetMapping("/paperByMail")
    public Paper paperByMail(@RequestParam String mail,@RequestParam int conId){
    	List<Paper> list= serv.paperByMail(mail);
    	for(Paper p:list) {
    		int c=p.getConfId();
    		if(c==conId) {
    			return p;
    		}
    	}
    	return null;
    }
}

