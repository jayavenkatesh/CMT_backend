package com.jaya.cmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaya.cmt.conference;
import com.jaya.cmt.user;
import com.jaya.cmt.repository.conferenceRepository;

@Service
public class conferenceService {

	@Autowired
	private conferenceRepository conferenceRepo;
	
//	public conference createConference(conference conference, user creator) {
//        conference.setCreator(creator);
//        return conferenceRepo.save(conference);
//    }
	public conference createConference(conference conference) {
        return conferenceRepo.save(conference);
    }

    public List<conference> getAllConferences() {
        return conferenceRepo.findAll();
    }

    public List<conference> getConferencesByUser(user user) {
        return conferenceRepo.findByMail(user.getEmail());
    }

	public Optional<conference> getConfById(int id) {
		return conferenceRepo.findById(id);
	}

	
}
