package com.bootcamp.app;

import java.util.Set;

public class User {
	
	private String username;
	private String email;
	
	public User(String username, String email) {
		this.username = username;
		this.setEmail(email);
	}
	
	public void addTags(Set<String> tags) {
		TagsManager.getInstance().addTags(tags);
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
