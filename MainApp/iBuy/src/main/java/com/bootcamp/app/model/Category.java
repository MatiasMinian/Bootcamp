package com.bootcamp.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES")
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "descrption")
	private String description;

	/* *** CONSTRUCTORS *** */

	public Category() {
	}

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}