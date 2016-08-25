package com.bootcamp.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post {
	
	private String title;
	private String text;
	private Calendar date = Calendar.getInstance();
	private Set<String> tags = new HashSet<>();
	private User user;
	private List<User> likes = new ArrayList<>();
	
	public Post(String title, String text, Set<String> tags, User user) {
		this.title = title;
		this.text = text;
		if (tags != null) {
			tags.addAll(tags);
		}
		this.user = user;
	}
	
	public void addLike(User user) {
		likes.add(user);
		
	}
	
	/* *** GETTERS & SETTERS *** */
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}
}
