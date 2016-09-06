package com.bootcamp.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "birth_date")
	private Calendar birthDate;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "last_login")
	private Calendar lastLogin = Calendar.getInstance();
	
	/* *** CONSTRUCTORS *** */
	
	public User(){}
	
	public User(String firstName, String lastName, String username, Calendar birthDate, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.birthDate = birthDate;
		this.email = email;
	}
	
	/* *** GETTERS & SETTERS *** */

	public Long getId() {
		return id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}
}