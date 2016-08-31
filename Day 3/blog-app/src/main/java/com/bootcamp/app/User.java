package com.bootcamp.app;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class User extends Subscribable {
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	// TODO How to implement the subscribers and persist them
	
	/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Post> myPosts = new ArrayList<>();*/
	
	public User(){}
	
	public User(String username, String email) {
		this.username = username;
		this.setEmail(email);
	}
	
	public void addTags(Set<Tag> tags) {
		TagsManager.getInstance().addTags(tags);
	}
	
	public Post createPost(String title, String text, Set<Tag> tags) {
		Post post = new Post(title, text, tags, this);
		PostsManager.getInstance().addPost(post);
		notifyNewPost(post);
		return post;
	}
	
	public Post createPost(String title, String text, Set<Tag> tags, Group group) {
		Post post = new Post(title, text, tags, this, group);
		PostsManager.getInstance().addGroupPost(post, group.getName());
		group.notifyNewPost(post);
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

	/*
	public List<Post> getMyPosts() {
		return myPosts;
	}

	public void setMyPosts(List<Post> myPosts) {
		this.myPosts = myPosts;
	}
	*/
}
