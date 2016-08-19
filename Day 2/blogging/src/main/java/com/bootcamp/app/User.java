package com.bootcamp.app;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class User {
	
	private String username;
	private SortedSet<Post> posts = new TreeSet<>();
	
	public User(String username) {
		this.username = username;
	}
	
	public Post createPost(String title, String text, Set<String> tags) {
		Post post = new Post(title, text, tags);
		posts.add(post);
		if (tags != null) {
			addTags(tags);
		}
		return post;
	}
	
	public void deletePost(Post post) {
		if (posts.remove(post)) {
			System.out.println("Post deleted.");
		} else {
			System.out.println("Post wasn't deleted");
		}
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

	public SortedSet<Post> getPosts() {
		return posts;
	}

	public void setPosts(SortedSet<Post> posts) {
		this.posts = posts;
	}
}
