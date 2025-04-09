package com.jaya.cmt.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jaya.cmt.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer>{
	Optional<user> findByEmail(String email);
	Optional<user> findByPassword(String password);

	Optional<user> findByIdAndEmail(int userId, String email);
	void deleteByEmail(String mail);
	Optional<user> findByEmailAndPassword(String email, String password);
	
	
}
