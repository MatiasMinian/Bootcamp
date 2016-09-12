package com.bootcamp.app.persistence.managers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.persistence.daos.interfaces.ProductDAO;
import com.bootcamp.app.utils.HibernateUtil;

@Component
public class ProductManager {

	@Autowired
	ProductDAO productDAO;

	/* *** CONSTRUCTORS *** */

	public ProductManager() {
	}

	public ProductManager(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	/* *** METHODS *** */

	public Long saveNewProduct(Product product) {
		Long id = null;
		try {
			HibernateUtil.beginTransaction();
			id = productDAO.save(product);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		return id;
	}

	public void updateProduct(Product product) {
		try {
			HibernateUtil.beginTransaction();
			productDAO.update(product);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void deleteProduct(Product product) {
		try {
			HibernateUtil.beginTransaction();
			productDAO.delete(product);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public Product findProductById(Long id) {
		Product product = null;
		try {
			HibernateUtil.beginTransaction();
			product = (Product) productDAO.findById(Product.class, id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return product;
	}

	public List<Product> findProductsByWord(String word) {
		List<Product> products = new ArrayList<>();
		try {
			HibernateUtil.beginTransaction();
			products.addAll(productDAO.searchProduct(word));
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Product> sortProductsByCheapest() {
		List<Product> products = new ArrayList<>();
		try {
			HibernateUtil.beginTransaction();
			products.addAll(productDAO.sortByCheapest());
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return products;		
	}
	
	public List<Product> sortProductsByPriciest() {
		List<Product> products = new ArrayList<>();
		try {
			HibernateUtil.beginTransaction();
			products.addAll(productDAO.sortByPriciest());
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return products;		
	}
	
	public List<Product> filterProductsByCategory(Long categoryId) {
		List<Product> products = new ArrayList<>();
		try {
			HibernateUtil.beginTransaction();
			products.addAll(productDAO.filterByCategory(categoryId));
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return products;		
	}
	
	public List<Product> getProductsByUser(Long userId) {
		List<Product> products = new ArrayList<>();
		try {
			HibernateUtil.beginTransaction();
			products.addAll(productDAO.getByUser(userId));
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return products;		
	}

	/* *** GETTERS & SETTERS *** */

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
}