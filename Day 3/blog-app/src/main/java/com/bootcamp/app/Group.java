package com.bootcamp.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "GROUPS")
public class Group {

	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	// TODO How to implement the subscribers and persist them
	@Transient
	private SubscriptionsManager subscriptionsManager = new SubscriptionsManager();
	
	public Group(){}
	
	public Group(String name) {
		this.name = name;
	}
	
	public void subscribeUser(User user) {
		subscriptionsManager.subscribeUser(user);
	}
	
	public void notifyNewPost(Post post) {
		subscriptionsManager.notifyNewPost(post);
	}
	
	/* *** GETTERS & SETTERS *** */

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubscriptionsManager getSubscriptionsManager() {
		return subscriptionsManager;
	}

	public void setSubscriptionsManager(SubscriptionsManager subscriptionsManager) {
		this.subscriptionsManager = subscriptionsManager;
	}	
}
