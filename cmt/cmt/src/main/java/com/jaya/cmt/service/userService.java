package com.jaya.cmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jaya.cmt.user;
import com.jaya.cmt.repository.userRepository;

//import org.springframework.security.core.Authentication;
@Service
public class userService {
	@Autowired
	private userRepository userRepo;

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	public user register(user user) {
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	public user findByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
	public user findByEmail(String email,String password) {
        user user= userRepo.findByEmail(email).orElse(null);
        user user1= userRepo.findByPassword(password).orElse(null);
        if(user==user1) {
        	return user;
        }
        return null;
        
    }
	public List<user> getAllUsers() {
		return userRepo.findAll();
	}
	public user findByEmailForConf(String userEmail) {
		user user=userRepo.findByEmail(userEmail).orElse(null);
		String s=user.getRole();
		if(s.equals("chairPerson")) {
			return user;
		}
		return null;
	}
	
	public void changeEmail(int userId, String currentEmail, String newEmail, String confirmEmail) throws Exception {
        // Validate emails
        if (!newEmail.equals(confirmEmail)) {
            throw new Exception("New email and confirm email do not match.");
        }

        // Find the user by ID and current email
        user user = userRepo.findByIdAndEmail(userId, currentEmail)
                .orElseThrow(() -> new Exception("Current email is incorrect or user not found."));

        // Update email
        user.setEmail(newEmail);
        userRepo.save(user);
    }
	public user findById(int id) {
		return userRepo.findById(id).orElse(null);
	}
	
	
	
	public void changePassword(Integer userId, String oldPassword, String newPassword, String confirmPassword) throws Exception {
        // Check if new passwords match
        if (!newPassword.equals(confirmPassword)) {
            throw new Exception("New password and confirm password do not match.");
        }

        // Validate new password requirements
        if (!isValidPassword(newPassword)) {
            throw new Exception("Password must be at least 12 characters and contain uppercase, lowercase, number, and special character.");
        }
        

        // Retrieve user and check old password
        user user = userRepo.findById(userId)
                .orElseThrow(() -> new Exception("User not found."));
        
        if (!oldPassword.equals(user.getPassword())) {
            throw new Exception("Old password is incorrect.");
        }

        // Update the password
        user.setPassword(newPassword);
        userRepo.save(user);
    }
	
    private boolean isValidPassword(String password) {
        return password.length() >= 7 &&
               password.matches(".*[A-Z].*") &&       // at least one uppercase
               password.matches(".*[a-z].*") &&       // at least one lowercase
               password.matches(".*[0-9].*") &&       // at least one digit
               password.matches(".*[~!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/].*"); // at least one special character
    }
	public Optional<user> userByMail(String mail) {
		return userRepo.findByEmail(mail);
	}
	public boolean findByEmailBolean(String email) {
		user u= userRepo.findByEmail(email).orElse(null);
		return u==null?false:true;
	}
	public void deleteAccount(String mail) {
		userRepo.deleteByEmail(mail);
		
	}
	public user findByEmailAndPassword(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password).orElse(null);
    }
	

}
