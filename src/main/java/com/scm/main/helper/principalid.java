package com.scm.main.helper;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;


public class principalid {

	
	public static String getEmailofLoggedInUser(Authentication authentication) {
		
//		identify the provider 
		
		if(authentication instanceof OAuth2AuthenticatedPrincipal) {
			 var token = (OAuth2AuthenticationToken)authentication;
			 String id = token.getAuthorizedClientRegistrationId();
			
//google 
			 if(id.equalsIgnoreCase("google")) {
				 System.out.println("getting data from google");
				 System.out.println(authentication.getName());
				 return authentication.getName();
				
			 	}
		
//github 
			else if(id.equalsIgnoreCase("github")) {
				 System.out.println("getting data from github");
				 System.out.println(authentication.getName());
				 return authentication.getName();
				}	
		}
//self
		else {
			 System.out.println("getting data from database");
			 System.out.println(authentication.getName());
			return authentication.getName();
		}
		
		
		
		return "";
	}
	
}
