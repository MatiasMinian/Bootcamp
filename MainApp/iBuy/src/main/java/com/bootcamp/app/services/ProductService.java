package com.bootcamp.app.services;

import org.springframework.stereotype.Service;

import com.bootcamp.app.model.Product;

@Service
public class ProductService {
	
	/* *** CONSTRUCTORS *** */
	
	public ProductService() {
	}
	
	/* *** METHODS *** */
	
	public boolean isReserved(Product product) {
		return product.getReservation() != null;	
	}
}
