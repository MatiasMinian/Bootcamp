package com.bootcamp.app;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionsManager {
	
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

	public List<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}
}
