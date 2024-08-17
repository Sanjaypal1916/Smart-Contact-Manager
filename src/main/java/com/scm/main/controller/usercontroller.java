package com.scm.main.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.main.entities.user;
import com.scm.main.helper.principalid;
import com.scm.main.service.userservice;

@Controller
@RequestMapping("/user")
public class usercontroller {
	private Logger logger =LoggerFactory.getLogger(usercontroller.class);
	@Autowired
	private userservice service;
	
	
	
	@PostMapping("/dashboard")
	public String dashboard() {
		return "userdashboard";
		
	}
	@GetMapping("/dashboard")
	public String dashboard2() {
		return "userdashboard";
		
	}
	
	@GetMapping("/profile")
	public String profile(Model model, Authentication authentication) {
		
		String usernem=principalid.getEmailofLoggedInUser(authentication);
		logger.info(usernem);
		user u2 = service.getuserbyemail(usernem);
	if(u2==null) {
		
		model.addAttribute("LoggedInUser",null );}
	else {

		logger.info(u2.getUseremail());
		logger.info(u2.getUsername());
		
		model.addAttribute("LoggedInUser",u2 );
	}
	
		return "userprofile";
		
	}
	
	

}
