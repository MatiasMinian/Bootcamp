package com.bootcamp.app;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USERS")
public class User {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Transient
	private SubscriptionsManager subscriptionsManager = new SubscriptionsManager();
	
	public User(){}
	
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
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
