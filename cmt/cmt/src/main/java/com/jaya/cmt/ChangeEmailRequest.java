package com.jaya.cmt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object for email change operation.
 * Validates current email, new email, and confirmation email.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEmailRequest {
    
    @NotBlank(message = "User ID is mandatory")
    private Integer userId;
    
    @Email(message = "Please provide a valid current email address")
    @NotBlank(message = "Current email is mandatory")
    private String currentEmail;
    
    @Email(message = "Please provide a valid new email address")
    @NotBlank(message = "New email is mandatory")
    private String newEmail;
    
    @Email(message = "Please provide a valid confirmation email address")
    @NotBlank(message = "Confirmation email is mandatory")
    private String confirmEmail;
}
