package com.scm.main.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity(name="user")
@Table(name="user")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class user implements UserDetails{
	
	//details
		@Id
		private String userid;
		@Column(name= "username")
		private String username;
		@Column(unique=true, nullable=false)
		private String useremail;
		private String userpassword;
		@Column(length=1000)
		private String about;
		@Column(length=1000)
		private String pfp;
		private String phone;
		
	//infromation
		private boolean enabled=true;
		private boolean emailverified= false;
		private boolean phoneverified=false;
		
	//SELF GOOGLE, FACEBOOK, TWITTER, LINKEDIN , GITHUB;
		@Enumerated(value= EnumType.STRING)
		private providers provider=providers.SELF;
		private String provideruserid;
		
	//mapping 
		@OneToMany(mappedBy ="user",cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY  )
		private List<contacts> contacts= new ArrayList<>();

		
//for authorities
		@ElementCollection(fetch = FetchType.EAGER)
		private List<String> roleList= new ArrayList<>();
		
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			//list4 of roles(user, admin)
			//collection of simplegrantedauthorities(roles(admin, user))
			Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
			return roles;
		}
		
		@Override
		public String getUsername() {
			return this.useremail;
		}

		@Override
		public String getPassword() {
			return this.userpassword;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
		
		


	
}
