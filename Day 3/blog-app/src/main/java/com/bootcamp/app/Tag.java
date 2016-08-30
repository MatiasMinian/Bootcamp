package com.bootcamp.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAGS")
public class Tag {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", unique = true, nullable = false)
	String name;
	
	public Tag(){}
	
	public Tag(String name) {
		this.name = name;
	}
	
	/* *** GETTERS & SETTERS *** */
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
