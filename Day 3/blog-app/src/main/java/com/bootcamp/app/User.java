package com.bootcamp.app;

import java.util.Set;

public class User {
	
	private String username;
	private String email;
	private SubscriptionsManager subscriptionsManager = new SubscriptionsManager();
	
	public User(String username, String email) {
		this.username = username;
		this.setEmail(email);
	}
	
	public void subscribeUser(User user) {
		subscriptionsManager.subscribeUser(user);
	}
	
	public void addTags(Set<Tag> tags) {
		TagsManager.getInstance().addTags(tags);
	}
	
	public Post createPost(String title, String text, Set<Tag> tags) {
		Post post = new Post(title, text, tags, this);
		PostsManager.getInstance().addPost(post);
		subscriptionsManager.notifyNewPost(post);
		return post;
	}
	
	public Post createPost(String title, String text, Set<Tag> tags, Group group) {
		Post post = new Post(title, text, tags, this);
		PostsManager.getInstance().addGroupPost(post, group.getName());
		// TODO Notify post created to subscribers	
		return post;		
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

	public SubscriptionsManager getSubscriptionsManager() {
		return subscriptionsManager;
	}

	public void setSubscriptionsManager(SubscriptionsManager subscriptionsManager) {
		this.subscriptionsManager = subscriptionsManager;
	}
}
