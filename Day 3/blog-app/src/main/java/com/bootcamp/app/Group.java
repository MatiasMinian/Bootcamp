package com.bootcamp.app;

public class Group {

	private String name;
	private SubscriptionsManager subscriptionsManager = new SubscriptionsManager();
	
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

	public String getName() {
		return name;
	}

	public void setId(String name) {
		this.name = name;
	}

	public SubscriptionsManager getSubsManager() {
		return subscriptionsManager;
	}

	public void setSubsManager(SubscriptionsManager subsManager) {
		this.subscriptionsManager = subsManager;
	}
}
