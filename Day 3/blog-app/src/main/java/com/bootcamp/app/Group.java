package com.bootcamp.app;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Group extends Subscribable {
	
	@Column(name = "name", unique = true)
	private String name;
	
	// TODO How to implement the subscribers and persist them
	
	public Group(){}
	
	public Group(String name) {
		this.name = name;
	}
	
	/* *** GETTERS & SETTERS *** */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
