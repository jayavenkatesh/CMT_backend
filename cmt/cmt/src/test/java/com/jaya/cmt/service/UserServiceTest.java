package com.jaya.cmt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jaya.cmt.User;
import com.jaya.cmt.repository.userRepository;

/**
 * Unit tests for UserService class.
 * Tests user registration, authentication, email change, and password change operations.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Tests")
public class UserServiceTest {

    @Mock
    private userRepository userRepo;

    @InjectMocks
    private userService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1);
        testUser.setName("John Doe");
        testUser.setEmail("john@example.com");
        testUser.setPassword("SecurePass123!");
        testUser.setRole("author");
    }

    @Test
    @DisplayName("Register user successfully")
    void testRegisterUserSuccess() {
        // Arrange
        when(userRepo.save(testUser)).thenReturn(testUser);

        // Act
        User registeredUser = userService.register(testUser);

        // Assert
        assertNotNull(registeredUser);
        assertEquals("John Doe", registeredUser.getName());
        assertEquals("john@example.com", registeredUser.getEmail());
        verify(userRepo, times(1)).save(testUser);
    }

    @Test
    @DisplayName("Find user by email successfully")
    void testFindByEmailSuccess() {
        // Arrange
        when(userRepo.findByEmail("john@example.com")).thenReturn(Optional.of(testUser));

        // Act
        User foundUser = userService.findByEmail("john@example.com");

        // Assert
        assertNotNull(foundUser);
        assertEquals("john@example.com", foundUser.getEmail());
        verify(userRepo, times(1)).findByEmail("john@example.com");
    }

    @Test
    @DisplayName("Find user by email - User not found")
    void testFindByEmailNotFound() {
        // Arrange
        when(userRepo.findByEmail("notexist@example.com")).thenReturn(Optional.empty());

        // Act
        User foundUser = userService.findByEmail("notexist@example.com");

        // Assert
        assertNull(foundUser);
        verify(userRepo, times(1)).findByEmail("notexist@example.com");
    }

    @Test
    @DisplayName("Find all users")
    void testGetAllUsers() {
        // Arrange
        User user2 = new User(2, "Jane Doe", "author", "jane@example.com", "Pass123!");
        List<User> users = Arrays.asList(testUser, user2);
        when(userRepo.findAll()).thenReturn(users);

        // Act
        List<User> allUsers = userService.getAllUsers();

        // Assert
        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("Change email successfully")
    void testChangeEmailSuccess() throws Exception {
        // Arrange
        when(userRepo.findByIdAndEmail(1, "john@example.com"))
                .thenReturn(Optional.of(testUser));
        when(userRepo.save(testUser)).thenReturn(testUser);

        // Act
        userService.changeEmail(1, "john@example.com", "newemail@example.com", "newemail@example.com");

        // Assert
        assertEquals("newemail@example.com", testUser.getEmail());
        verify(userRepo, times(1)).findByIdAndEmail(1, "john@example.com");
        verify(userRepo, times(1)).save(testUser);
    }

    @Test
    @DisplayName("Change email - Emails do not match")
    void testChangeEmailMismatch() {
        // Act & Assert
        assertThrows(Exception.class, () -> {
            userService.changeEmail(1, "john@example.com", "new@example.com", "different@example.com");
        });
    }

    @Test
    @DisplayName("Change password successfully")
    void testChangePasswordSuccess() throws Exception {
        // Arrange
        when(userRepo.findById(1)).thenReturn(Optional.of(testUser));
        when(userRepo.save(testUser)).thenReturn(testUser);

        // Act
        userService.changePassword(1, "SecurePass123!", "NewSecure456!", "NewSecure456!");

        // Assert
        assertEquals("NewSecure456!", testUser.getPassword());
        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).save(testUser);
    }

    @Test
    @DisplayName("Change password - Passwords do not match")
    void testChangePasswordMismatch() {
        // Act & Assert
        assertThrows(Exception.class, () -> {
            userService.changePassword(1, "SecurePass123!", "NewSecure456!", "Different789!");
        });
    }

    @Test
    @DisplayName("Find user by email and password")
    void testFindByEmailAndPassword() {
        // Arrange
        when(userRepo.findByEmailAndPassword("john@example.com", "SecurePass123!"))
                .thenReturn(Optional.of(testUser));

        // Act
        User foundUser = userService.findByEmailAndPassword("john@example.com", "SecurePass123!");

        // Assert
        assertNotNull(foundUser);
        assertEquals("john@example.com", foundUser.getEmail());
        verify(userRepo, times(1)).findByEmailAndPassword("john@example.com", "SecurePass123!");
    }

    @Test
    @DisplayName("Delete user account")
    void testDeleteAccount() {
        // Arrange
        doNothing().when(userRepo).deleteByEmail("john@example.com");

        // Act
        userService.deleteAccount("john@example.com");

        // Assert
        verify(userRepo, times(1)).deleteByEmail("john@example.com");
    }

    @Test
    @DisplayName("Check if email exists")
    void testFindByEmailBoolean() {
        // Arrange
        when(userRepo.findByEmail("john@example.com")).thenReturn(Optional.of(testUser));

        // Act
        boolean exists = userService.findByEmailBolean("john@example.com");

        // Assert
        assertTrue(exists);
        verify(userRepo, times(1)).findByEmail("john@example.com");
    }
}
