package com.bootcamp.app.persistence.daos;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

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
public class ProductDAOImplTest {
	
	@Autowired
	ProductDAO productDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	UserDAO userDAO;

	Product nexus;
	User matias;
	Category phones;
	
	boolean deletionTest = false;
	
	
	@Before
	public void setUp() throws Exception {
		matias = new User("Matias", "Minian", "mat", Calendar.getInstance(), "mat@gmail.com");
		phones = new Category("phones", "smartphones");
		nexus = new Product("nexus", "phone", matias, phones, "img", new BigDecimal(1000), true);
		
		beginTransaction();
		categoryDAO.save(phones);
		userDAO.save(matias);
		productDAO.save(nexus);
		commitTransaction();		
	}

	@After
	public void tearDown() throws Exception {
		beginTransaction();
		if (!deletionTest) {
			productDAO.delete(nexus);
		}
		userDAO.delete(matias);
		categoryDAO.delete(phones);
		commitTransaction();
		
	}

	@Test
	public void testProductWasSaved() {
		beginTransaction();
		Product savedProduct = productDAO.findById(Product.class, nexus.getId());
		commitTransaction();
		assertNotNull(savedProduct);
		assertTrue(savedProduct.getName().equals("nexus"));
		assertTrue(savedProduct.getDescription().equals("phone"));
		assertTrue(savedProduct.isNew());
	}
	
	@Test
	public void testProductWasUpdated() {
		nexus.setName("samsung");
		nexus.setNew(false);
		beginTransaction();
		productDAO.update(nexus);
		Product updatedProduct = productDAO.findById(Product.class, nexus.getId());
		commitTransaction();
		assertTrue(updatedProduct.getName().equals("samsung"));
		assertTrue(!updatedProduct.isNew());
	}
	
	@Test
	public void testProductWasDeleted() {
		deletionTest = true;
		beginTransaction();
		productDAO.delete(nexus);
		assertNull(productDAO.findById(Product.class, nexus.getId()));
		commitTransaction();
	}
	
	@Test
	public void testSearchProducts() {
		Product samsung = new Product("samsung", "phone", matias, phones, "img", new BigDecimal(1000), true);
		Product iphone = new Product("apple", "apple", matias, phones, "img", new BigDecimal(1000), true);
		beginTransaction();
		productDAO.save(samsung);
		productDAO.save(iphone);
		commitTransaction();
		
		beginTransaction();
		List<Product> products = productDAO.searchProduct("phone");	
		commitTransaction();
		
		beginTransaction();
		productDAO.delete(samsung);
		productDAO.delete(iphone);
		commitTransaction();
		assertTrue(products.size() == 2);
	}
}
