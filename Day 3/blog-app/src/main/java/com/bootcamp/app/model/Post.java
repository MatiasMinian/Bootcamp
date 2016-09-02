package com.bootcamp.app.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "POSTS")
public class Post {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date", nullable = false)
	private Calendar creationDate = Calendar.getInstance();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "POSTS_TAGS", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = true)
	private Group group;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "LIKES", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> likes = new ArrayList<>();
	
	/* *** CONSTRUCTORS *** */
	
	public Post(){}
	
	public Post(String title, String text, Set<Tag> tags, User user) {
		this.title = title;
		this.text = text;
		if (tags != null) {
			this.tags.addAll(tags);
		}
		this.user = user;
	}
	
	public Post(String title, String text, Set<Tag> tags, User user, Group group) {
		this.title = title;
		this.text = text;
		if (tags != null) {
			this.tags.addAll(tags);
		}
		this.user = user;
		this.group = group;
	}
	
	/* *** GETTERS & SETTERS *** */

	public Long getId() {
		return id;
	}

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

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}
}
