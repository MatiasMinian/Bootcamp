package com.bootcamp.app.persistence.daos.interfaces;

import java.util.List;

import com.bootcamp.app.model.Product;

public interface ProductDAO extends GenericDAO<Product, Long> {
	
	public List<Product> searchProduct(String word);
	public List<Product> sortByCheapest();
	public List<Product> sortByPriciest();
	public List<Product> filterByCategory(Long categoryId);
	public List<Product> getByUser(Long userId);
}
