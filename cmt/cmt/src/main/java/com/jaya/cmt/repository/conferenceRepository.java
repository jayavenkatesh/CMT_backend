package com.jaya.cmt.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaya.cmt.conference;

@Repository
public interface conferenceRepository extends JpaRepository<conference, Integer>  {

	//List<conference> findByCreatorId(Integer creatorId);
	//List<conference> findByEmail(String email);

	List<conference> findByMail(String email);
}
