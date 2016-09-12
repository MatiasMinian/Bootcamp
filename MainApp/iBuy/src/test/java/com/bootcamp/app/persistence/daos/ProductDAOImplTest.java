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

	Product nexus, ipad, samsung;
	User matias;
	Category phones, apple;
	
	boolean deletionTest = false;
	
	
	@Before
	public void setUp() throws Exception {
		matias = new User("Matias", "Minian", "mat", Calendar.getInstance(), "mat@gmail.com");
		phones = new Category("phones", "smartphones");
		apple = new Category("apple", "apple products");
		nexus = new Product("nexus", "phone", matias, phones, "img", new BigDecimal(1000), true);
		ipad = new Product("ipad", "apple", matias, apple, "img", new BigDecimal(3000), true);
		samsung = new Product("samsung", "phone", matias, phones, "img", new BigDecimal(2200), true);
		
		beginTransaction();
		categoryDAO.save(phones);
		categoryDAO.save(apple);
		userDAO.save(matias);
		productDAO.save(ipad);
		productDAO.save(nexus);
		productDAO.save(samsung);
		commitTransaction();		
	}

	@After
	public void tearDown() throws Exception {
		beginTransaction();
		if (!deletionTest) {
			productDAO.delete(nexus);
		}
		productDAO.delete(ipad);
		productDAO.delete(samsung);
		userDAO.delete(matias);
		categoryDAO.delete(phones);
		categoryDAO.delete(apple);
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
		beginTransaction();
		List<Product> products = productDAO.searchProduct("phone");	
		commitTransaction();
		
		assertTrue(products.size() == 2);
	}
	
	@Test
	public void testSortByCheapest() {
		beginTransaction();
		List<Product> products = productDAO.sortByCheapest();	
		commitTransaction();
		
		assertTrue(products.get(0).getName().equals("nexus"));
		assertTrue(products.get(1).getName().equals("samsung"));
		assertTrue(products.get(2).getName().equals("ipad"));
	}
	
	@Test
	public void testSortByPriciest() {
		beginTransaction();
		List<Product> products = productDAO.sortByPriciest();	
		commitTransaction();
		
		assertTrue(products.get(0).getName().equals("ipad"));
		assertTrue(products.get(1).getName().equals("samsung"));
		assertTrue(products.get(2).getName().equals("nexus"));
	}
	
	@Test
	public void testFilterByCategory() {
		beginTransaction();
		List<Product> productsInPhones = productDAO.filterByCategory(phones.getId());
		List<Product> productsInApple = productDAO.filterByCategory(apple.getId());
		commitTransaction();
		
		assertTrue(productsInApple.size() == 1);
		assertTrue(productsInApple.get(0).getName().equals("ipad"));
		
		assertTrue(productsInPhones.size() == 2);
		assertTrue(productsInPhones.get(0).getName().equals("nexus"));
		assertTrue(productsInPhones.get(1).getName().equals("samsung"));		
	}
	
	@Test
	public void testGetByUser() {
		User pablo = new User("Pablo", "Alice", "pab", Calendar.getInstance(), "pab@gmail.com");
		Product LG = new Product("nexus", "phone", pablo, phones, "img", new BigDecimal(1600), true);
		beginTransaction();
		userDAO.save(pablo);
		productDAO.save(LG);
		commitTransaction();
		
		beginTransaction();
		List<Product> products = productDAO.getByUser(matias.getId());
		commitTransaction();
		
		beginTransaction();
		productDAO.delete(LG);
		userDAO.delete(pablo);
		commitTransaction();
		
		assertTrue(products.size() == 3);		
	}
}