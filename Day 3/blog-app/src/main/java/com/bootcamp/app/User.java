package com.bootcamp.app;

public class User {
	
	private String username;
	private String email;
	
	public User(String username, String email) {
		this.username = username;
		this.setEmail(email);
	}
	
	/* *** GETTERS & SETTERS *** */
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
