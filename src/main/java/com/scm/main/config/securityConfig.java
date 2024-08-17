package com.scm.main.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {
	
//beans for the user details service
	
//first step------->UserDetailService method with userdetailsservice class
//second step------>create authenticationprovider using authenticationprovider class
//third step------->create objects of userdetails(we'll need to fetch the data from the database) and password encoder and call them in dao
//forth step------->create a userdetails class or implement the class in the enitiies only
//fifth step------->generate unimplemented methods from the userdetails
//sixth step------->now create customuserdetailservice whic implements userdetail service
//seventh step----->now implement the unimplemented methods and bring data from jpa
//eighth step------>now inject customuserdetailservice object in securityconfiguration and use th object as user details
//ninth step------->build securityfilterchain method for (blocking th required page only)
//tenth step------->permit the and authenticate the urls your want 
//eleventh step---->format the login and logout urls 
	
//OAuth security
//		--->add oauth client starter dependency
//		--->google ->
//					-client id
//					-client secret 
//		--->Add the OAuth login configuration
//		--->login page/ login and successhandler
//	 	--->successhandler gets the data...save the data on provider information
		
	
	
	@Autowired
	private OAuthAuthenticationSuccessHandler handler;
		
	
	@Bean
	public UserDetailsService detailservice() {
		
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("ADMIN","USER")
//				.build();
//		user
//		UserDetails user1 = User.withDefaultPasswordEncoder()
//				.username("user2")
//				.password("password")
//				.roles("ADMIN","USER")
//				.build();
//		
//		
////for in memorydetails 
//		var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user, user1);
//		return InMemoryUserDetailsManager;
		return null;
	}
	
	
	@Autowired
	private securitycustomuserdetailservice userdetailservice;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
//for service registration
		DaoAuthenticationProvider daouthprovider= new DaoAuthenticationProvider();
//userdetailservice ka object
		daouthprovider.setUserDetailsService(userdetailservice);
//passwordencoder ka object
		daouthprovider.setPasswordEncoder(passwordencoder());
		return daouthprovider;
	}
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity) throws Exception {
		
		
		httpsecurity.authorizeHttpRequests(authorize->{
			authorize.requestMatchers("user/**").authenticated();
			authorize.anyRequest().permitAll();
			});
		
		httpsecurity.formLogin(formlogin->{
			formlogin.loginPage("/loginin");
			formlogin.loginProcessingUrl("/processingauth");
			formlogin.successForwardUrl("/user/dashboard");
//			formlogin.failureForwardUrl("/loginin?error=true");
			formlogin.usernameParameter("email");
			formlogin.passwordParameter("password");
//failurehandler to pass a specific method after failure	
//			formlogin.failureHandler(new AuthenticationFailureHandler() {
//				@Override
//				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//						AuthenticationException exception) throws IOException, ServletException {
//						throw new UnsupportedOperationException("unimplemented method 'onAuthenticationfailure");
//				}	
//			});
////successhandler to pass a specific method after success		
//			formlogin.successHandler(new AuthenticationSuccessHandler() {
//				@Override
//				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//						Authentication authentication) throws IOException, ServletException {
//					throw new UnsupportedOperationException("unimplemented method 'onAuthenticationSuccess");	
//				}
//		});
			
			
			
		});
		
		
		httpsecurity.csrf(AbstractHttpConfigurer::disable);
		
		httpsecurity.logout(logoutForm->{
			logoutForm.logoutUrl("/dologout");
			logoutForm.logoutSuccessUrl("/loginin?logout=true");	
		});
		
		
//Oauth	configuration
		httpsecurity.oauth2Login(oauth->{
			oauth.loginPage("/loginin");
			oauth.successHandler(handler);
		});
		
		
		
		
		
		
		
		
		return httpsecurity.build();
		
	}
	
	
	
	
	
	
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
}
