package com.bootcamp.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

	public void sendEmail(Post post, User user) {
		String filename = user.getEmail() + ".txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Post: " + post.getTitle() + ". Author: " + post.getUser().getUsername());
			bw.newLine();
			bw.close();
		} catch (IOException ex) {
			ex.printStackTrace();			
		}
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
