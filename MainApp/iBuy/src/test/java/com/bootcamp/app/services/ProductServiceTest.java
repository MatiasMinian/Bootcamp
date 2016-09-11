package com.bootcamp.app.services;

import static com.bootcamp.app.utils.HibernateUtil.beginTransaction;
import static com.bootcamp.app.utils.HibernateUtil.commitTransaction;
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
import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.interfaces.CategoryDAO;
import com.bootcamp.app.persistence.daos.interfaces.ProductDAO;
import com.bootcamp.app.persistence.daos.interfaces.ReservationDAO;
import com.bootcamp.app.persistence.daos.interfaces.UserDAO;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IBuyApplication.class)
public class ProductServiceTest {
	
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	ReservationDAO reservationDAO;
	@Autowired
	ProductService productService;
	
	Category phones;
	User pablo, matias;
	Product nexus;
	Reservation pabloResNexus;

	@Before
	public void setUp() throws Exception {
		phones = new Category("phones", "smartphones");
		pablo = new User("Pablo", "Alice", "pab", Calendar.getInstance(), "pab@gmail.com");
		matias = new User("Matias", "Minian", "mat", Calendar.getInstance(), "mat@gmail.com");
		nexus = new Product("nexus", "phone", matias, phones, "img", new BigDecimal(1000), true);
		pabloResNexus = new Reservation(pablo, nexus);

		beginTransaction();
		categoryDAO.save(phones);
		userDAO.save(pablo);
		userDAO.save(matias);
		productDAO.save(nexus);
		reservationDAO.save(pabloResNexus);
		commitTransaction();
	}

	@After
	public void tearDown() throws Exception {
		beginTransaction();
		reservationDAO.delete(pabloResNexus);
		productDAO.delete(nexus);
		userDAO.delete(matias);
		userDAO.delete(pablo);
		categoryDAO.delete(phones);		
		commitTransaction();
	}

	@Test
	public void testProductIsReserved() {
		beginTransaction();
		Product nexusPhone = productDAO.findById(Product.class, nexus.getId());
		commitTransaction();
		assertTrue(productService.isReserved(nexusPhone));
		assertTrue(nexusPhone.getReservation().getBuyer().getFirstName().equals("Pablo"));
	}
}
