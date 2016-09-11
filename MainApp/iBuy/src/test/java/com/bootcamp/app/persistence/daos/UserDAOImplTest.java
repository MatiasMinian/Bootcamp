package com.bootcamp.app.persistence.daos;

import static com.bootcamp.app.utils.HibernateUtil.beginTransaction;
import static com.bootcamp.app.utils.HibernateUtil.commitTransaction;
import static org.junit.Assert.*;

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
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.interfaces.UserDAO;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IBuyApplication.class)
public class UserDAOImplTest {
	
	@Autowired
	UserDAO userDAO;
	
	User matias;
	boolean deletionTest = false;

	@Before
	public void setUp() throws Exception {
		matias = new User("Matias", "Minian", "mat", Calendar.getInstance(), "mat@gmail.com");
		
		beginTransaction();
		userDAO.save(matias);
		commitTransaction();
	}

	@After
	public void tearDown() throws Exception {
		beginTransaction();
		if (!deletionTest) {
			userDAO.delete(matias);
		}
		commitTransaction();
	}

	@Test
	public void testUserWasSaved() {
		beginTransaction();
		User savedUser = userDAO.findById(User.class, matias.getId());
		commitTransaction();
		assertNotNull(savedUser);
		assertTrue(savedUser.getFirstName().equals("Matias"));
		assertTrue(savedUser.getUsername().equals("mat"));
	}

	@Test
	public void testUserWasUpdated() {
		matias.setUsername("matimin");
		beginTransaction();
		userDAO.update(matias);
		User updatedUser = userDAO.findById(User.class, matias.getId());
		commitTransaction();
		assertTrue(updatedUser.getUsername().equals("matimin"));
	}

	@Test
	public void testUserWasDeleted() {
		deletionTest = true;
		beginTransaction();
		userDAO.delete(matias);
		assertNull(userDAO.findById(User.class, matias.getId()));
		commitTransaction();
	}
}