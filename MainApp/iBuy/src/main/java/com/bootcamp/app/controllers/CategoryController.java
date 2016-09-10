package com.bootcamp.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.app.model.Category;
import com.bootcamp.app.model.responses.MessageResponse;
import com.bootcamp.app.persistence.managers.CategoryManager;
import com.bootcamp.app.utils.Constants;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryManager categoryManager;
	
	public CategoryController() {
	}
	
	public CategoryController(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	
	@RequestMapping(value = "/api/categories", produces = "application/json", method = RequestMethod.GET)
	public List<Category> getCategories() {
		return categoryManager.findAllCategories();
	}
	
	@RequestMapping(value = "/api/create/category", consumes = "application/json", method = RequestMethod.POST)
	public String createCategory(@RequestBody Category category) {
		categoryManager.saveNewCategory(category);
		return "Category was created successfully";
	}
	
	@RequestMapping(value = "/api/update/category", consumes = "application/json", method = RequestMethod.POST)
	public String updateCategory(@RequestBody Category category) {
		categoryManager.updateCategory(category);
		return "Category was updated successfully";
	}
	
	@RequestMapping(value = "api/delete/category", method = RequestMethod.POST)
	public MessageResponse deleteCategory(@RequestBody Category categoryResponse) {
		Category category = categoryManager.findCategoryById(categoryResponse.getId());
		if (categoryManager.getCategoryProductsQuantity(category.getId()) == 0) {
			categoryManager.deleteCategory(category);
			return new MessageResponse(Constants.MSG_SUCCESS, "Category was deleted successfully");
		} else {
			return new MessageResponse(Constants.MSG_FAILURE, "Category can't be deleted. There are products with this category");			
		}	
	}
}