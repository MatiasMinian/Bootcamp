package com.bootcamp.app.services;

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
import com.bootcamp.app.model.User;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IBuyApplication.class)
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	Calendar lastLogin = Calendar.getInstance();
	User matias; 

	@Before
	public void setUp() throws Exception {
		matias = new User("Matias", "Minian", "mat", Calendar.getInstance(), "mat@gmail.com");
		matias.setLastLogin(lastLogin);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserIsDeleteable() {
		lastLogin.set(2011, 2, 10);
		matias.setLastLogin(lastLogin);
		assertTrue(userService.isDeleteable(matias));
	}
	
	@Test
	public void testUserIsNotDeletable() {
		lastLogin = Calendar.getInstance();
		lastLogin.add(Calendar.DAY_OF_MONTH, -10);
		matias.setLastLogin(lastLogin);
		assertTrue(!userService.isDeleteable(matias));
	}
}
