package com.scm.main.form;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class userform {
	@NotBlank(message="username is required")
	@Size(min=3, message="min 3 characters is required")
	private String name;
	@NotBlank(message = "Email is required")
	@Email(message="invalid email adress")
	private String email;
	
	@NotBlank(message = "password is required")
	@Size(min=8, max=8, message="password size should of 8 digits")
	private String password;
	@NotBlank(message="contact is required")
	@Size(min=10, max=10, message="Invalid contact number")
	private String contact;
	@NotBlank(message="About is required")
	private String about;

	@Override
	public String toString() {
		return "userform [name=" + name + ", email=" + email + ", password=" + password + ", contact=" + contact
				+ ", about=" + about + "]";
	}

	
	
}
