package com.jaya.cmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class emailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String text) {
    	try {
    		SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
    	}
    	catch(Exception e){
    		System.err.println("Error sending email: " + e.getMessage());
    	}
        
    }
}

