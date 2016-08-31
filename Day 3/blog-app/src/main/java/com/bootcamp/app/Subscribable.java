package com.bootcamp.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIBABLES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Subscribable {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SUBSCRIPTIONS", joinColumns = @JoinColumn(name = "subscribable_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> subscribers = new ArrayList<>();
	
	public void subscribeUser(User user) {
		subscribers.add(user);
	}
	
	public void notifyNewPost(Post post) {
		subscribers.forEach(user -> {
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
	
	public int getId() {
		return id;
	}

	public List<User> getSubscribers() {
		return subscribers;
	}	

	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}
}
