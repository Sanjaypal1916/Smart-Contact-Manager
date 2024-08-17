package com.scm.main.config;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.main.entities.providers;
import com.scm.main.entities.user;
import com.scm.main.helper.appconstraints;
import com.scm.main.repository.userrepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	public userrepo repo;

	Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("OAuthAuthenticationSuccessHandler");

//identify the provider 		
		var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
		String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

		logger.info(authorizedClientRegistrationId);

		DefaultOAuth2User user2 = (DefaultOAuth2User) authentication.getPrincipal();
		user2.getAttributes().forEach((key, value) -> {
			logger.info(key + "->" + value);
		});

		user user = new user();
		user.setUserid(UUID.randomUUID().toString());
		user.setEmailverified(true);
		user.setRoleList(List.of(appconstraints.ROLE_USER));
		
		String email = null;

//google - google attributes
		if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

		}

//github- github attributes

		else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {

			user.setUseremail(user2.getAttribute("email") != null ? user2.getAttribute("email")
					: "https://github.com/" + user2.getAttribute("login").toString());
			user.setUsername(user2.getAttribute("login"));
			user.setContacts(null);
			user.setPfp(user2.getAttribute("avatar_url"));
			user.setProvider(providers.GITHUB);
			user.setProvideruserid(user2.getName());
			user.setAbout("hey i have signed up using github");
		
			email=user2.getAttribute("email") != null ? user2.getAttribute("email")
					: "https://github.com/" + user2.getAttribute("login").toString();

		}

//facebook- face attributes

		else {

		}

////taking data from the database
//		DefaultOAuth2User user1 = (DefaultOAuth2User) authentication.getPrincipal();
//		
//		logger.info(user1.getName());
//		
//		user1.getAttributes().forEach((key, value)->{
//			logger.info("{} => {}",key, value);
//		});
//		
//		logger.info(user1.getAuthorities().toString());
//////	

		/*
		 * String email = user1.getAttribute("name"); String name =
		 * user1.getAttribute("email"); String picture = user1.getAttribute("picture");
		 * 
		 * 
		 * //create user and save it the database user u = new user();
		 * u.setUserid(UUID.randomUUID().toString()); u.setUsername(name);
		 * u.setUseremail(email); u.setPfp(picture); u.setUserpassword("password");
		 * u.setProvider(providers.GOOGLE); u.setEnabled(true);
		 * u.setEmailverified(true); u.setProvideruserid(user1.getName());
		 * u.setRoleList(List.of(appconstraints.ROLE_USER));
		 * u.setAbout("hey im loggedin using google");
		 */
		Optional<user> u2= repo.findByUseremail(email);
		if (u2 == null) {
			repo.save(user);
		} else {
			logger.info("user already exists");
		}

		new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");

	}

}
