package com.bootcamp.app.model.requests;

public class NewProductRequest {
	
	private String name;
	private String description;
	private String categoryId;
	private String price;
	private String imageURL;
	private String condition;
	private String userId;
	
	public NewProductRequest() {
	}
	
	public NewProductRequest(String name, String description, String categoryId, String price, String imageURL, String isNew, String userId) {
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
		this.price = price;
		this.imageURL = imageURL;
		this.condition = isNew;
		this.userId = userId;		
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getCondition() {
		return condition;
	}

	public void setIsNew(String condition) {
		this.condition = condition;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
