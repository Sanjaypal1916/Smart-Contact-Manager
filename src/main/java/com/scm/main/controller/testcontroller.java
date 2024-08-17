package com.scm.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.main.entities.user;
import com.scm.main.form.userform;
import com.scm.main.helper.message;
import com.scm.main.helper.messagetype;
import com.scm.main.service.userservice;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class testcontroller {
	
	@Autowired
	private userservice service;
	
	@RequestMapping("/testing")
	public String test() {
		return "test";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/services")
	public String service() {
		return "service";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/contactus")
	public String contactUs() {
		return "contactus";
	}
	
	
//
	@GetMapping("/loginin")
	public String login() {
		return "login";
	}
	
//registration
	@GetMapping("/signup")
	public String signup(Model model) {
		userform userf = new userform();

		//userf.setName("snaj");
		model.addAttribute("userform", userf);
		//default values
		
		return "signup";
	}
	
	@PostMapping("/doregister")
	public String doregister(@Valid @ModelAttribute userform userform, BindingResult  result, HttpSession session) {
		System.out.print("do register methood called");
		//fetch
		System.out.print(userform);
		
		//validate
		if(result.hasErrors()) {
			return "signup";
		}
		
		
		//save to db
//		userform==> user
//		user user2 = user.builder()
//				.username(userform.getName())
//				.useremail(userform.getEmail())
//				.userpassword(userform.getPassword())
//				.about(userform.getAbout())
//				.phone(userform.getContact())
//				.pfp("image/contacts.png")
//				.build();
		
		user user2 = new user();
		user2.setUsername(userform.getName());
		user2.setUserpassword(userform.getPassword());
		user2.setUseremail(userform.getEmail());
		user2.setAbout(userform.getAbout());
		user2.setPhone(userform.getContact());
		user2.setPfp("images/contacts.png");
		
		user saveduser= service.saveuser(user2);
		 System.out.println(saveduser);
		
		//message
		 message msg =message.builder().content("registration successful").type(messagetype.success).build();
		 session.setAttribute("message", msg);
		 
		 
		//redirect to signup
		return "redirect:/signup";
	}

	public testcontroller(userservice service) {
		super();
		this.service = service;
	}
	
	

}
