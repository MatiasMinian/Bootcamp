package com.bootcamp.app.persistence.daos;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bootcamp.app.IBuyApplication;
import com.bootcamp.app.model.Category;
import com.bootcamp.app.model.Product;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.interfaces.CategoryDAO;
import com.bootcamp.app.persistence.daos.interfaces.ProductDAO;
import com.bootcamp.app.persistence.daos.interfaces.UserDAO;

import static com.bootcamp.app.utils.HibernateUtil.beginTransaction;
import static com.bootcamp.app.utils.HibernateUtil.commitTransaction;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IBuyApplication.class)
public class CategoryDAOImplTest {

	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	UserDAO userDAO;

	Category phones;
	boolean deletionTest = false;

	@Before
	public void setUp() throws Exception {
		phones = new Category("phones", "smartphones");

		beginTransaction();
		categoryDAO.save(phones);
		commitTransaction();
	}

	@After
	public void tearDown() throws Exception {
		beginTransaction();
		if (!deletionTest) {
			categoryDAO.delete(phones);
		}
		commitTransaction();
	}

	@Test
	public void testCategoryWasSaved() {
		beginTransaction();
		Category savedCategory = categoryDAO.findById(Category.class, phones.getId());
		commitTransaction();
		assertNotNull(savedCategory);
		assertTrue(savedCategory.getName().equals("phones"));
		assertTrue(savedCategory.getDescription().equals("smartphones"));
	}

	@Test
	public void testCategoryWasUpdated() {
		phones.setName("cellphones");
		beginTransaction();
		categoryDAO.update(phones);
		Category updatedCategory = categoryDAO.findById(Category.class, phones.getId());
		commitTransaction();
		assertTrue(updatedCategory.getName().equals("cellphones"));
	}

	@Test
	public void testCategoryWasDeleted() {
		deletionTest = true;
		beginTransaction();
		categoryDAO.delete(phones);
		assertNull(categoryDAO.findById(Category.class, phones.getId()));
		commitTransaction();
	}
	
	@Test
	public void testCategoryProductsQuantity() {
		User matias = new User("Matias", "Minian", "mat", Calendar.getInstance(), "mat@gmail.com");
		Product nexus = new Product("nexu", "phone", matias, phones, "img", new BigDecimal(1000), true);
		beginTransaction();	
		userDAO.save(matias);
		productDAO.save(nexus);
		Long quantity = categoryDAO.categoryProductsQuantity(phones.getId());
		productDAO.delete(nexus);
		userDAO.delete(matias);
		commitTransaction();
		assertTrue(quantity == 1);
	}
}
