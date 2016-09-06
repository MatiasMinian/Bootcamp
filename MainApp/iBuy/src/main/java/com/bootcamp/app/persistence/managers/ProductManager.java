package com.bootcamp.app.persistence.managers;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.ProductDAO;

public class ProductManager {

	@Autowired
	private ProductDAO productDAO;

	/* *** CONSTRUCTORS *** */

	public ProductManager(){}

	public ProductManager(ProductDAO productDAO) {
		this.productDAO = productDAO;		
	}

	/* *** METHODS *** */

	public void saveNewProduct(Product product) {
		try {
			HibernateUtil.beginTransaction();
			productDAO.save(product);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void updateProduct(Product product) {
		try {
			HibernateUtil.beginTransaction();
			productDAO.save(product);
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
			product = (Product) productDAO.findOne(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
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