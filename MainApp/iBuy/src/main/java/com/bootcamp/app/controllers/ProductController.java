package com.bootcamp.app.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.app.model.Category;
import com.bootcamp.app.model.Product;
import com.bootcamp.app.model.User;
import com.bootcamp.app.model.requests.NewProductRequest;
import com.bootcamp.app.model.responses.MessageResponse;
import com.bootcamp.app.model.responses.ProductResponse;
import com.bootcamp.app.model.responses.SimpleProductResponse;
import com.bootcamp.app.persistence.managers.CategoryManager;
import com.bootcamp.app.persistence.managers.ProductManager;
import com.bootcamp.app.persistence.managers.UserManager;
import com.bootcamp.app.services.ProductService;
import com.bootcamp.app.utils.Constants;

@RestController
public class ProductController {
	
	@Autowired
	ProductManager productManager;
	@Autowired
	UserManager userManager;
	@Autowired
	CategoryManager categoryManager;
	@Autowired
	ProductService productService;
	
	public ProductController() {
	}
	
	@RequestMapping(value = "/api/products/sortby={sorter}", produces = "application/json", method = RequestMethod.GET)
	public List<SimpleProductResponse> getProducts(@PathVariable String sorter) {
		List<Product> products; 	
		if (sorter.equals("cheapest")) {
			products = productManager.sortProductsByCheapest();
		} else {
			products = productManager.sortProductsByPriciest();
		}
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
	
	@RequestMapping(value = "/api/products/{id}", produces = "application/json", method = RequestMethod.GET)
	public List<SimpleProductResponse> getProductsByUser(@PathVariable String id) {
		List<Product> products = productManager.getProductsByUser(Long.valueOf(id));
		List<SimpleProductResponse> productsResponse = new ArrayList<>();
		products.forEach(product -> {
			SimpleProductResponse simpleProduct = new SimpleProductResponse(product);
			simpleProduct.setReserved(productService.isReserved(product));
			productsResponse.add(simpleProduct);
		});
		return productsResponse;
	}
	
	@RequestMapping(value = "/api/product/sold", produces = "application/json", method = RequestMethod.POST)
	public MessageResponse productWasSold(@RequestBody SimpleProductResponse simpleProduct) {
		Product product = productManager.findProductById(simpleProduct.getId());
		productManager.deleteProduct(product);
		return new MessageResponse(Constants.MSG_SUCCESS, "Congratulations. You sold your product");
	}
	
	@RequestMapping(value = "/api/create/product", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
	public MessageResponse createProduct(@RequestBody NewProductRequest productRequest) {
		User owner = userManager.findUserById(Long.valueOf(productRequest.getUserId()));
		Category category = categoryManager.findCategoryById(Long.valueOf(productRequest.getCategoryId()));
		Product product = new Product(productRequest.getName(), productRequest.getDescription(), owner, category, 
				productRequest.getImageURL(), BigDecimal.valueOf(Double.valueOf(productRequest.getPrice())), 
				Boolean.valueOf(productRequest.getCondition()));
		productManager.saveNewProduct(product);
		return new MessageResponse(Constants.MSG_SUCCESS, "Your product was created successfully");
	}
	
	@RequestMapping(value = "/api/products/search={searchText}", produces = "application/json", method = RequestMethod.GET)
	public List<SimpleProductResponse> searchProducts(@PathVariable String searchText) {
		List<Product> products = productManager.findProductsByWord(searchText);	
		List<SimpleProductResponse> productsResponse = new ArrayList<>();
		products.forEach(product -> {
			SimpleProductResponse simpleProduct = new SimpleProductResponse(product);
			simpleProduct.setReserved(productService.isReserved(product));
			productsResponse.add(simpleProduct);
		});
		return productsResponse;
	}
}