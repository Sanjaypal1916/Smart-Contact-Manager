package com.scm.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.main.entities.user;

@Repository
public interface userrepo extends JpaRepository<user, String> {

	Optional<user> findByUseremail(String email);

	

	//extra methods for db relationoperation
	//custom query methods;
	//custom finder methods

}
