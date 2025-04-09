package com.jaya.cmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaya.cmt.ChangeEmailRequest;
import com.jaya.cmt.conference;
import com.jaya.cmt.user;
import com.jaya.cmt.service.userService;

@RestController
//@RequestMapping("/VITCC/user")
@CrossOrigin
public class userController {
	
	@Autowired
	private userService userService;
	
	@PostMapping("/register")
	public ResponseEntity<user> register(@RequestBody user user){
		user registeredUser=userService.register(user);
		return ResponseEntity.ok(registeredUser);
	}
	
//	@PostMapping("/login")
//    public ResponseEntity<user> loginUser(@RequestBody user user) {
//        user foundUser = userService.findByEmail(user.getEmail(),user.getPassword());
//        if (foundUser != null) {
//            return ResponseEntity.ok(user);
//        }
//        //return ResponseEntity.badRequest().body("Invalid credentials");
//        return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
//    }
	@PostMapping("/login")
    public ResponseEntity<user> loginUser(@RequestBody user user) {
        user foundUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        
        if (foundUser != null) {
            return ResponseEntity.ok(foundUser); // ✅ Return the actual user
        }
        
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }


	@GetMapping("/getAllUsers")
	public ResponseEntity<List<user>> getAllConferences() {
        List<user> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
	
	
	@GetMapping("/userByEmail")
	public user getUserByEmail(@RequestParam String email) {
		return userService.findByEmail(email);
	}
	@GetMapping("/getUserByEmailBoolean")
	public boolean getUserByEmailBoolean(@RequestParam String email) {
		return userService.findByEmailBolean(email);
	}
	@GetMapping("/roleByEmail")
	public String getRoleByEmail(@RequestParam String email) {
		user user= userService.findByEmail(email);
		if(user==null) {
			return "";
		}
		return user.getRole();
	}
	@GetMapping("/userById")
	public user getUserById(@RequestParam int id) {
		return userService.findById(id);
	}
	
	@PutMapping("/changeEmail")
    public ResponseEntity<String> changeEmail(@RequestBody ChangeEmailRequest emailclass) {
		
		try {
            userService.changeEmail(
            		emailclass.getUserId(),
            		emailclass.getCurrentEmail(),
            		emailclass.getNewEmail(),
            		emailclass.getConfirmEmail()
            );
            return ResponseEntity.ok("Email changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	@PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam Integer userId,
                                                 @RequestParam String oldPassword,
                                                 @RequestParam String newPassword,
                                                 @RequestParam String confirmPassword) {
        try {
            userService.changePassword(userId, oldPassword, newPassword, confirmPassword);
            return ResponseEntity.ok("Password changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteAccount(@RequestParam String mail){
		userService.deleteAccount(mail);
		return new ResponseEntity<>("Account Deleted",HttpStatus.OK);
	}
	

}
