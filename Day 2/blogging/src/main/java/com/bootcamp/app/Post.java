package com.bootcamp.app;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Post implements Comparable<Post> {
	
	private String title;
	private String text;
	private Calendar date = Calendar.getInstance();
	private Set<String> tags = new HashSet<>();
	
	public Post(String title, String text, Set<String> tags) {
		this.title = title;
		this.text = text;
		if (tags != null) {
			this.tags = tags;
		}
	}
	
	public void show() {
		System.out.println(title);
		System.out.println(text);
		System.out.println("Date: " + date.getTime());
		System.out.print("Tags:");
		tags.forEach(tag -> System.out.print(" " + tag));
		System.out.println();
	}
	
	@Override
	public int compareTo(Post anotherPost) {
		if (date == null || anotherPost.getDate() == null) {
			return 0;
		}
		if (date.equals(anotherPost)) {
			return 0;
		} else if (date.after(anotherPost)) {
			return 1;
		} else {
			return -1;
		}
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
}
