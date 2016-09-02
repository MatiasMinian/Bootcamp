package com.bootcamp.app;

import java.util.List;

import com.bootcamp.app.model.Group;
import com.bootcamp.app.model.Post;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.managers.GroupManager;
import com.bootcamp.app.persistence.managers.UserManager;

public class SubscriptionsService {

	private UserManager userManager;
	private GroupManager groupManager;
	private Mailer mailer;
	
	/* *** CONSTRUCTORS *** */
	
	public SubscriptionsService(){}
	
	public SubscriptionsService(UserManager userManager, GroupManager groupManager, Mailer mailer) {
		this.userManager = userManager;
		this.groupManager = groupManager;		
		this.mailer = mailer;
	}
	
	/* *** METHODS *** */

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
		mailer.sendEmail("New Post", 
				"Post: " + post.getTitle() + ". Author: " + post.getUser().getUsername(), 
				user.getEmail());
	}
	
	/* *** GETTERS & SETTERS *** */

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public GroupManager getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
	}

	public Mailer getMailer() {
		return mailer;
	}

	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}
}