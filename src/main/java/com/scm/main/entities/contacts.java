package com.scm.main.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class contacts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String cid;
	private String cname;
	private String cemail;
	private String cphone;
	private String caddress;
	private String cpicture;
	@Column(length=1000)
	private String cdescription;
	private boolean favourite=false;
	private String weblink;
	private String linkedin;
	
	@OneToMany(mappedBy ="contacts",cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY  )
	private List<sociallinks> sociallinks= new ArrayList<>();
	
	@ManyToOne
	private user user;
	

}
