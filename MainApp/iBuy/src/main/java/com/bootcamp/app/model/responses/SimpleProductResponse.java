package com.bootcamp.app.model.responses;

import java.math.BigDecimal;

import com.bootcamp.app.model.Product;

public class SimpleProductResponse {
	
	private Long id;
	private String name;
	private String description;
	private boolean reserved;
	private String categoryName;
	private BigDecimal price;
	private boolean isNew;
	
	/* *** CONSTRUCTORS *** */
	
	public SimpleProductResponse() {
	}
	
	public SimpleProductResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.categoryName = product.getCategory().getName();
		this.price = product.getPrice();
		this.isNew = product.isNew();		
	}
	
	/* *** GETTERS & SETTERS *** */

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

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public Long getId() {
		return id;
	}
}