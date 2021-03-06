package com.bootcamp.app.persistence.managers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.app.model.Category;
import com.bootcamp.app.persistence.daos.interfaces.CategoryDAO;
import com.bootcamp.app.utils.HibernateUtil;

@Component
public class CategoryManager {

	@Autowired
	CategoryDAO categoryDAO;

	/* *** CONSTRUCTORS *** */

	public CategoryManager() {
	}

	public CategoryManager(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	/* *** METHODS *** */

	public Long saveNewCategory(Category category) {
		Long id = null;
		try {
			HibernateUtil.beginTransaction();
			id = categoryDAO.save(category);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		System.out.println("Category was saved successfully");
		return id;
	}

	public void updateCategory(Category category) {
		try {
			HibernateUtil.beginTransaction();
			categoryDAO.update(category);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		System.out.println("Category was updated successfully");
	}

	public void deleteCategory(Category category) {
		try {
			HibernateUtil.beginTransaction();
			categoryDAO.delete(category);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		System.out.println("Category was deleted successfully");
	}

	public Category findCategoryById(Long id) {
		Category category = null;
		try {
			HibernateUtil.beginTransaction();
			category = (Category) categoryDAO.findById(Category.class, id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		System.out.println("Category was found successfully");
		return category;
	}

	public List<Category> findAllCategories() {
		List<Category> categories = new ArrayList<>();
		try {
			HibernateUtil.beginTransaction();
			categories.addAll(categoryDAO.findAll(Category.class));
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	public Long getCategoryProductsQuantity(Long categoryId) {
		Long quantity = 0l;
		try {
			HibernateUtil.beginTransaction();
			quantity = categoryDAO.categoryProductsQuantity(categoryId);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return quantity;		
	}

	/* *** GETTERS & SETTERS *** */

	public CategoryDAO getSCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
}