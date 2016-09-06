package com.bootcamp.app.persistence.managers;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.app.model.Category;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.CategoryDAO;

public class CategoryManager {

	@Autowired
	private CategoryDAO categoryDAO;

	/* *** CONSTRUCTORS *** */

	public CategoryManager(){}

	public CategoryManager(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;		
	}

	/* *** METHODS *** */

	public void saveNewCategory(Category category) {
		try {
			HibernateUtil.beginTransaction();
			categoryDAO.save(category);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void updateCategory(Category category) {
		try {
			HibernateUtil.beginTransaction();
			categoryDAO.save(category);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
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
	}

	public Category findCategoryById(Long id) {
		Category category = null;
		try {
			HibernateUtil.beginTransaction();
			category = (Category) categoryDAO.findOne(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return category;
	}

	/* *** GETTERS & SETTERS *** */

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
}
