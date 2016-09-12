package com.bootcamp.app.model.responses;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.model.User;

public class ProductResponse {
	
	private SimpleProductResponse product;
	private SimpleUserResponse user;
	
	/* *** CONSTRUCTORS *** */
	
	public ProductResponse() {
	}
	
	public ProductResponse(Product product, User user) {
		this.product = new SimpleProductResponse(product);
		this.user = new SimpleUserResponse(user);
	}
	
	/* *** GETTERS & SETTERS *** */

	public SimpleProductResponse getProduct() {
		return product;
	}

	public void setProduct(SimpleProductResponse product) {
		this.product = product;
	}

	public SimpleUserResponse getUser() {
		return user;
	}

	public void setUser(SimpleUserResponse user) {
		this.user = user;
	}	
}