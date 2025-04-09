package com.jaya.cmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jaya.cmt.Paper;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer> {
	 @Query("SELECT p FROM Paper p WHERE p.userMail = :userMail AND p.confId = :confId")
	 Optional<Paper> findByUserEmailAndConfId(@Param("userMail") String userMail, @Param("confId") Integer confId);

	List<Paper> findByUserMail(String mail);
}

