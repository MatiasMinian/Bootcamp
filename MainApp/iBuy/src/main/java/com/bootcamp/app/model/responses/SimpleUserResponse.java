package com.bootcamp.app.model.responses;

import java.util.Calendar;

import com.bootcamp.app.model.User;
import com.bootcamp.app.utils.DatesUtil;

public class SimpleUserResponse {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String birthDate;
	private String email;
	private boolean deleteable;
	
	/* *** CONSTRUCTORS *** */
	
	public SimpleUserResponse() {
	}
	
	public SimpleUserResponse(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.birthDate = DatesUtil.formatDate(user.getBirthDate());
		this.email = user.getEmail();
		this.deleteable = isDeleteable(user.getLastLogin());
	}
	
	/* *** METHODS *** */
	
	private boolean isDeleteable(Calendar lastLogin) {
		return DatesUtil.differenceInDays(lastLogin, Calendar.getInstance()) > 30;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDeleteable() {
		return deleteable;
	}

	public void setDeleteable(boolean deleteable) {
		this.deleteable = deleteable;
	}
}