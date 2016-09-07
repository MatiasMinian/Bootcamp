package com.bootcamp.app.persistence.managers;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.repositories.interfaces.ProductDAO;

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
		System.out.println("Product was saved successfully");
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
		System.out.println("Product was updated successfully");
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
		System.out.println("Product was deleted successfully");
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
		System.out.println("Product was found successfully");
		return product;
	}

	/* *** GETTERS & SETTERS *** */

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
}