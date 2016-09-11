package com.bootcamp.app.services;

import static com.bootcamp.app.utils.HibernateUtil.beginTransaction;
import static com.bootcamp.app.utils.HibernateUtil.commitTransaction;
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
import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.interfaces.CategoryDAO;
import com.bootcamp.app.persistence.daos.interfaces.ProductDAO;
import com.bootcamp.app.persistence.daos.interfaces.ReservationDAO;
import com.bootcamp.app.persistence.daos.interfaces.UserDAO;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IBuyApplication.class)
public class ReservationServiceTest {
	
	@Autowired
	ReservationService reservationService;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	ReservationDAO reservationDAO;
	
	Category phones;
	User pablo, matias;
	Product nexus, iphone;
	Reservation pabloResNexus, pabloResIphone;
	

	@Before
	public void setUp() throws Exception {
		phones = new Category("phones", "smartphones");
		matias = new User("Matias", "Minian", "mat", Calendar.getInstance(), "mat@gmail.com");
		pablo = new User("Pablo", "Alice", "pab", Calendar.getInstance(), "pab@gmail.com");
		nexus = new Product("nexus", "phone", matias, phones, "img", new BigDecimal(1000), true);
		iphone = new Product("iphone", "apple", matias, phones, "img", new BigDecimal(3000), true);
		pabloResNexus = new Reservation(pablo, nexus);
		pabloResIphone = new Reservation(pablo, iphone);
		Calendar oldReservation = Calendar.getInstance();
		oldReservation.set(2015, 8, 20);
		pabloResIphone.setDate(oldReservation);
		
		beginTransaction();
		categoryDAO.save(phones);
		userDAO.save(pablo);
		userDAO.save(matias);
		productDAO.save(nexus);
		productDAO.save(iphone);
		reservationDAO.save(pabloResNexus);
		reservationDAO.save(pabloResIphone);
		commitTransaction();
	}

	@After
	public void tearDown() throws Exception {
		beginTransaction();
		reservationDAO.delete(pabloResNexus);
		productDAO.delete(iphone);
		productDAO.delete(nexus);
		userDAO.delete(matias);
		userDAO.delete(pablo);
		categoryDAO.delete(phones);
		commitTransaction();
	}

	@Test
	public void testOldReservationsWereCancelled() {
		reservationService.updateReservations();
		beginTransaction();
		List<Reservation> reservations = reservationDAO.findAll(Reservation.class);
		commitTransaction();
		
		assertTrue(reservations.size() == 1);
		assertTrue(reservations.get(0).getProduct().getName().equals("nexus"));
	}
}