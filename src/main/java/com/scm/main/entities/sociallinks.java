package com.scm.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class sociallinks {
	
	@Id
	private Long id;
	private String Link;
	private String title;
	
	@ManyToOne
	private contacts contacts;
	

}
