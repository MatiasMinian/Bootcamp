package com.bootcamp.app.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	// TODO Should it be lazy ?
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User owner;

	// TODO Should it be lazy ?
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Column(name = "image_URL", nullable = false)
	private String imageURL;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "is_new", nullable = false)
	private boolean isNew;

	/* *** CONSTRUCTORS *** */

	public Product(){}

	public Product(String name, String description, User owner, Category category, 
			String imageURL, BigDecimal price, boolean isNew) {
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.category = category;
		this.imageURL = imageURL;
		this.price = price;
		this.isNew = isNew;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
}