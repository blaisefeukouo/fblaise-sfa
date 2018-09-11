package com.example.fblaisesfa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.fblaisesfa.AttributesNames;

@Entity
@Table(name = "t_user")
public class User extends SfaDomain {

	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	// @NotEmpty(message = "*Please provide an email")
	private String email;
	@Column(name = "password")
	// @Length(min = 5, message = "*Your password must have at least 5
	// characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;
	@Column(name = "first_name")
	// @NotEmpty(message = "*Please provide your firt_name")
	private String firstName;
	@Column(name = "last_name")
	// @NotEmpty(message = "*Please provide your last name")
	private String lastName;
	@Column(name = "user_name")
	// @NotEmpty(message = "*Please provide your user name")
	private String userName;
	@Column(name = "isactive")
	private boolean isActive;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name = "role_id") )
	private Set<Role> roles;

	public User(String email, String password, String firstName, String lastName, String userName, boolean isActive) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.isActive = isActive;
	}

	public User() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void copyValuesFrom(User user, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.userName = user.userName;
		this.password = bCryptPasswordEncoder.encode(user.password);
		this.email = user.email;
		// this.isActive = user.isActive;
	}

	public boolean isAdmin() {
		for (Role role : roles) {
			if (role.getRole().equals(AttributesNames.Role_Admin)) {
				return true;
			}
		}
		return false;
	}

}
