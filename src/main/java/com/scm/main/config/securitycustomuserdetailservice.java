package com.scm.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.main.repository.userrepo;

@Service
public class securitycustomuserdetailservice implements UserDetailsService{
	@Autowired
	private userrepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUseremail(username)
				.orElseThrow(() ->new UsernameNotFoundException("user not found"));
	
	}

}
