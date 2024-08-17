package com.scm.main.serviceimp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.main.entities.user;
import com.scm.main.helper.appconstraints;
import com.scm.main.helper.resourcenotfoundexception;
import com.scm.main.repository.userrepo;
import com.scm.main.service.userservice;

@Service
public class userserviceimpl implements userservice{
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private userrepo repo;
	
	public userserviceimpl(userrepo repo) {
		super();
		this.repo = repo;
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public user saveuser(user user) {
		String id = UUID.randomUUID().toString();
		user.setUserid(id);
		user.setUserpassword(passwordencoder.encode(user.getPassword()));
		user.setRoleList(List.of(appconstraints.ROLE_USER));
		
		logger.info(user.getProvider().toString());
	return repo.save(user);
	}

	@Override
	public Optional<user> getUserById(String id) {
		
		return repo.findById(id);
	}

	@Override
	public Optional<user> updateUser(user user) {
		repo.findById(user.getUserid()).orElseThrow(()->new resourcenotfoundexception("user not found"));
	 return null;
	 
	
	}

	@Override
	public void deleteUser(String id) {
		user user = repo.findById(id)
				.orElseThrow(()->new resourcenotfoundexception("user not found"));
		repo.delete(user);
		
	}

	@Override
	public boolean isUserExist(String userid) {
		user user= repo.findById(userid).orElse(null);
		return user != null ? true: false;
	}

	@Override
	public boolean isUserExistByEmail(String email) {
//		user user = repo.findbyEmail(email).orElse(null);
		return false;
	}

	@Override
	public List<user> getAllUsers() {
		List<user> user1 = repo.findAll(); 
		return user1;
	}

	@Override
	public user getuserbyemail(String email){
		return repo.findByUseremail(email).orElse(null);
	}

}
