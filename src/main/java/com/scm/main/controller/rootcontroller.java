package com.scm.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.main.entities.user;
import com.scm.main.helper.principalid;
import com.scm.main.service.userservice;

@ControllerAdvice
public class rootcontroller {

	private Logger logger =LoggerFactory.getLogger(usercontroller.class);

	@Autowired
	private userservice service;
	@ModelAttribute
	public void addLoggedInUserInformation(Model model, Authentication authentication) {
		
	if(authentication==null) {
		return ;
	}
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
	}
}
