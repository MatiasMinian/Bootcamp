package com.bootcamp.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class User {

	private String username;
	private List<Post> posts = new ArrayList<>();

	public User(String username) {
		this.username = username;
	}

	public Post createPost(String title, String text, Set<String> tags) {
		Post post = new Post(title, text, tags);
		posts.add(0, post);;
		if (tags != null) {
			addTags(tags);
		}
		return post;
	}

	public void deletePost(Post post) {
		posts.remove(post);
	}

	public void showRecentPosts(int quantity) {
		Iterator<Post> it = posts.iterator();
		for (int i = 0; i < quantity; i++) {
			if (!it.hasNext()) {
				return;
			}
			it.next().show();
		}
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
