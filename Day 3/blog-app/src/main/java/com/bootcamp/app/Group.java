package com.bootcamp.app;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private String name;
	private List<User> users = new ArrayList<>();
	
	public Group(String name) {
		this.setId(name);
	}

	public void notifyNewPost(Post post) {
		users.forEach(user -> {
			if (user != post.getUser()) {
				sendEmail(post, user);
			}
		});
	}

	private void sendEmail(Post post, User user) {
		Mailer.getInstance().sendEmail("New Post", 
				"Post: " + post.getTitle() + ". Author: " + post.getUser().getUsername(), 
				user.getEmail());
	}

	/* *** GETTERS & SETTERS *** */

	public void suscribeUser(User user) {
		users.add(user);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setId(String name) {
		this.name = name;
	}
}
