package com.jaya.cmt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaya.cmt.conference;
import com.jaya.cmt.user;
import com.jaya.cmt.service.conferenceService;
import com.jaya.cmt.service.userService;

@RestController
//@RequestMapping("VITCC/conference")
@CrossOrigin
public class conferenceController {
	@Autowired
	private userService userService;
	@Autowired
    private conferenceService conferenceService;
	
	@PostMapping("/createConference")
    public ResponseEntity<String> createConference(@RequestBody conference conference) {
        
        conferenceService.createConference(conference);
        return ResponseEntity.ok("Conference created successfullyy");
    }
	//@PostMapping("/createConference")
//    public ResponseEntity<String> createConference(@RequestBody conference conference, @RequestParam String userEmail) {
//        user creator = userService.findByEmailForConf(userEmail);
//        if (creator == null) {
//        	return new ResponseEntity<>("Authors are not authorised to create Conference",HttpStatus.FORBIDDEN);
//            //return ResponseEntity.badRequest().build();
//        }
//        //conference createdConference = conferenceService.createConference(conference, creator);
//        conferenceService.createConference(conference, creator);
//        return ResponseEntity.ok("Conference created successfully");
//    }

    @GetMapping("/allConferences")
    public ResponseEntity<List<conference>> getAllConferences() {
        List<conference> conferences = conferenceService.getAllConferences();
        return ResponseEntity.ok(conferences);
    }

    @GetMapping("/MyConferences")
    public ResponseEntity<List<conference>> getUserConferences(@RequestParam String userEmail) {
        user user = userService.findByEmail(userEmail);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        List<conference> conferences = conferenceService.getConferencesByUser(user);
        return ResponseEntity.ok(conferences);
    }
    @GetMapping("/getConfById")
    public Optional<conference> getConfById(@RequestParam int id) {
    	return conferenceService.getConfById(id);
    }
}
