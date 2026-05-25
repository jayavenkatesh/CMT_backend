package com.jaya.cmt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
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

import com.jaya.cmt.Conference;
import com.jaya.cmt.User;
import com.jaya.cmt.repository.conferenceRepository;

/**
 * Unit tests for ConferenceService class.
 * Tests conference creation, retrieval, and filtering operations.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ConferenceService Tests")
public class ConferenceServiceTest {

    @Mock
    private conferenceRepository conferenceRepo;

    @InjectMocks
    private conferenceService conferenceService;

    private Conference testConference;
    private User testUser;

    @BeforeEach
    void setUp() {
        testConference = new Conference();
        testConference.setId(1);
        testConference.setName("International Conference on AI 2024");
        testConference.setStartDate(Date.valueOf("2024-12-01"));
        testConference.setEndDate(Date.valueOf("2024-12-05"));
        testConference.setDescription("A premier conference on Artificial Intelligence");
        testConference.setMail("organizer@conference.com");

        testUser = new User();
        testUser.setId(1);
        testUser.setEmail("organizer@conference.com");
        testUser.setRole("chairPerson");
    }

    @Test
    @DisplayName("Create conference successfully")
    void testCreateConferenceSuccess() {
        // Arrange
        when(conferenceRepo.save(testConference)).thenReturn(testConference);

        // Act
        Conference createdConference = conferenceService.createConference(testConference);

        // Assert
        assertNotNull(createdConference);
        assertEquals("International Conference on AI 2024", createdConference.getName());
        assertEquals("organizer@conference.com", createdConference.getMail());
        verify(conferenceRepo, times(1)).save(testConference);
    }

    @Test
    @DisplayName("Get all conferences")
    void testGetAllConferences() {
        // Arrange
        Conference conf2 = new Conference();
        conf2.setId(2);
        conf2.setName("International Conference on ML 2024");
        List<Conference> conferences = Arrays.asList(testConference, conf2);
        when(conferenceRepo.findAll()).thenReturn(conferences);

        // Act
        List<Conference> allConferences = conferenceService.getAllConferences();

        // Assert
        assertNotNull(allConferences);
        assertEquals(2, allConferences.size());
        verify(conferenceRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("Get conferences by user email")
    void testGetConferencesByUser() {
        // Arrange
        List<Conference> userConferences = Arrays.asList(testConference);
        when(conferenceRepo.findByMail("organizer@conference.com")).thenReturn(userConferences);

        // Act
        List<Conference> conferences = conferenceService.getConferencesByUser(testUser);

        // Assert
        assertNotNull(conferences);
        assertEquals(1, conferences.size());
        assertEquals("organizer@conference.com", conferences.get(0).getMail());
        verify(conferenceRepo, times(1)).findByMail("organizer@conference.com");
    }

    @Test
    @DisplayName("Get conference by ID")
    void testGetConfById() {
        // Arrange
        when(conferenceRepo.findById(1)).thenReturn(Optional.of(testConference));

        // Act
        Optional<Conference> foundConference = conferenceService.getConfById(1);

        // Assert
        assertTrue(foundConference.isPresent());
        assertEquals("International Conference on AI 2024", foundConference.get().getName());
        verify(conferenceRepo, times(1)).findById(1);
    }

    @Test
    @DisplayName("Get conference by ID - Not found")
    void testGetConfByIdNotFound() {
        // Arrange
        when(conferenceRepo.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Conference> foundConference = conferenceService.getConfById(999);

        // Assert
        assertFalse(foundConference.isPresent());
        verify(conferenceRepo, times(1)).findById(999);
    }

    @Test
    @DisplayName("Get conferences when user has none")
    void testGetConferencesByUserEmpty() {
        // Arrange
        when(conferenceRepo.findByMail("noconference@example.com")).thenReturn(Arrays.asList());

        User emptyUser = new User();
        emptyUser.setEmail("noconference@example.com");

        // Act
        List<Conference> conferences = conferenceService.getConferencesByUser(emptyUser);

        // Assert
        assertNotNull(conferences);
        assertTrue(conferences.isEmpty());
        verify(conferenceRepo, times(1)).findByMail("noconference@example.com");
    }
}
