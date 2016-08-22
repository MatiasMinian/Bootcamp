package com.bootcamp.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Group {

	private List<User> users = new ArrayList<>();
	private List<Post> posts = new ArrayList<>();

	public void createPost(String title, String text, Set<String> tags, User user) {
		Post post = new Post(title, text, tags, user);
		posts.add(0, post);
		notifyNewPost(post);
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
