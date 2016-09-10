package com.bootcamp.app.persistence.daos.interfaces;

import com.bootcamp.app.model.Category;

public interface CategoryDAO extends GenericDAO<Category, Long> {
	
	public Long categoryProductsQuantity(Long categoryId);

}
