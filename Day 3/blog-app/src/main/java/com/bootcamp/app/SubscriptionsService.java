package com.bootcamp.app;

import java.util.List;

import com.bootcamp.app.model.Group;
import com.bootcamp.app.model.Post;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.managers.GroupManager;
import com.bootcamp.app.persistence.managers.UserManager;

public class SubscriptionsService {

	// TODO Should I use Dependency Injection
	private UserManager userManager;
	private GroupManager groupManager;

	public void subscribeToUser(User subscriber, User user) {
		user.getSubscribers().add(subscriber);
		userManager.updateUser(user);
	}

	public void subscribeToGroup(User subscriber, Group group) {
		group.getSubscribers().add(subscriber);
		groupManager.updateGroup(group);
	}

	public void notifyNewPost(Post post) {
		List<User> subscribers = null;
		if (post.getGroup() == null) {
			subscribers = post.getUser().getSubscribers();
		} else {
			subscribers = post.getGroup().getSubscribers();
			subscribers.remove(post.getUser());
		}
		if (subscribers.size() > 0) {
			subscribers.forEach(user -> sendEmail(post, user));
		}
	}
	
	private void sendEmail(Post post, User user) {
		Mailer.getInstance().sendEmail("New Post", 
				"Post: " + post.getTitle() + ". Author: " + post.getUser().getUsername(), 
				user.getEmail());
	}
}
