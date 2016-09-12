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
import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.interfaces.CategoryDAO;
import com.bootcamp.app.persistence.daos.interfaces.ProductDAO;
import com.bootcamp.app.persistence.daos.interfaces.ReservationDAO;
import com.bootcamp.app.persistence.daos.interfaces.UserDAO;

import static com.bootcamp.app.utils.HibernateUtil.beginTransaction;
import static com.bootcamp.app.utils.HibernateUtil.commitTransaction;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IBuyApplication.class)
public class ReservationDAOImplTest {

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
	Product nexus;
	Reservation pabloResNexus;
	boolean deletionTest = false;

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
		if (!deletionTest) {
			reservationDAO.delete(pabloResNexus);
		}
		productDAO.delete(nexus);
		userDAO.delete(matias);
		userDAO.delete(pablo);
		categoryDAO.delete(phones);		
		commitTransaction();
	}

	@Test
	public void testReservationWasSaved() {
		beginTransaction();
		Reservation savedReservation = reservationDAO.findById(Reservation.class, pabloResNexus.getId());
		commitTransaction();
		assertNotNull(savedReservation);
		assertTrue(savedReservation.getBuyer().getFirstName().equals("Pablo"));
		assertTrue(savedReservation.getProduct().getName().equals("nexus"));
	}

	@Test
	public void testReservationWasUpdated() {
		Calendar date = Calendar.getInstance();
		date.set(2015, 8, 12);
		pabloResNexus.setDate(date);
		beginTransaction();
		reservationDAO.update(pabloResNexus);
		Reservation updatedReservation = reservationDAO.findById(Reservation.class, pabloResNexus.getId());
		commitTransaction();
		assertTrue(updatedReservation.getDate().get(Calendar.YEAR) == 2015);
		assertTrue(updatedReservation.getDate().get(Calendar.MONTH) == 8);
		assertTrue(updatedReservation.getDate().get(Calendar.DAY_OF_MONTH) == 12);
	}

	@Test
	public void testReservationWasDeleted() {
		deletionTest = true;
		beginTransaction();
		reservationDAO.delete(pabloResNexus);
		assertNull(reservationDAO.findById(Reservation.class, pabloResNexus.getId()));
		commitTransaction();
	}
	
	@Test
	public void testGetReservationsByUser() {
		Product ipad = new Product("ipad", "apple", pablo, phones, "img", new BigDecimal(3000), true);
		Reservation matiasResIpad = new Reservation(matias, ipad);
		beginTransaction();
		productDAO.save(ipad);
		reservationDAO.save(matiasResIpad);
		commitTransaction();
		beginTransaction();
		List<Reservation> reservations = reservationDAO.getByUser(pablo.getId());
		commitTransaction();
		assertTrue(reservations.size() == 1);
		assertTrue(reservations.get(0).getProduct().getOwner().getFirstName().equals("Pablo"));
		
		beginTransaction();
		reservationDAO.delete(matiasResIpad);
		productDAO.delete(ipad);
		commitTransaction();
	}
}