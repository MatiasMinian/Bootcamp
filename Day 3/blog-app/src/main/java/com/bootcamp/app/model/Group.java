package com.bootcamp.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
public class Group {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "GROUPS_SUBSCRIPTIONS", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "subscriber_id"))
	private List<User> subscribers = new ArrayList<>();
	
	/* *** CONSTRUCTORS *** */
	
	public Group(){}
	
	public Group(String name) {
		this.name = name;
	}
	
	/* *** GETTERS & SETTERS *** */

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}
	
	

}
