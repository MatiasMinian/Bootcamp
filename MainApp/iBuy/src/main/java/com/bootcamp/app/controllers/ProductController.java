package com.bootcamp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.model.responses.ProductResponse;
import com.bootcamp.app.model.responses.SimpleProductResponse;
import com.bootcamp.app.persistence.managers.ProductManager;
import com.bootcamp.app.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductManager productManager;
	@Autowired
	ProductService productService;
	
	public ProductController() {
	}
	
	@RequestMapping(value = "/api/products", produces = "application/json", method = RequestMethod.GET)
	public List<SimpleProductResponse> getProducts() {
		List<Product> products = productManager.sortProductsByCheapest();	
		List<SimpleProductResponse> productsResponse = new ArrayList<>();
		products.forEach(product -> {
			SimpleProductResponse simpleProduct = new SimpleProductResponse(product);
			simpleProduct.setReserved(productService.isReserved(product));
			productsResponse.add(simpleProduct);
		});
		return productsResponse;
	}
	
	@RequestMapping(value = "/api/product/{id}", produces = "application/json", method = RequestMethod.GET)
	public ProductResponse getProductById(@PathVariable String id) {
		Product product = productManager.findProductById(Long.valueOf(id));
		ProductResponse productResponse = new ProductResponse(product, product.getOwner());
		productResponse.getProduct().setReserved(productService.isReserved(product));
		return productResponse;		
	}
}
