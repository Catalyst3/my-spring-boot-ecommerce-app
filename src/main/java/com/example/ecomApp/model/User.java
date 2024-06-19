package com.example.ecomApp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userID;
	
	@NotEmpty
	@Column (nullable = false)
	private String userFirstName;
	
	@NotEmpty
	@Column (nullable = false)
	private String userLastName;

	@NotEmpty
	@Column (nullable = false, unique = true)
	@Email (message = "{error.invalide_Email}")
	private String userEmail;
	
	@NotEmpty
	@Column (nullable = false)
	private String userPassword;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable (
			name = "user_role",
			joinColumns = {@JoinColumn (name = "USER_ID", referencedColumnName = "userID")} ,
							inverseJoinColumns = {@JoinColumn (name = "ROLE_ID", referencedColumnName = "roleID")}
			)
	private List<Roles> userRole;
	
	

}
