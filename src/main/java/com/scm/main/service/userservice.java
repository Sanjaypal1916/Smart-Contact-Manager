package com.scm.main.service;

import java.util.List;
import java.util.Optional;

import com.scm.main.entities.user;

public interface userservice {
	user saveuser(user user);
	Optional<user> getUserById(String user);
	Optional<user> updateUser(user user);
	void deleteUser(String id);
	boolean isUserExist(String userid);
	boolean isUserExistByEmail(String email);
	List<user> getAllUsers();
	user getuserbyemail(String email);
	
	//add more methods if required

}
