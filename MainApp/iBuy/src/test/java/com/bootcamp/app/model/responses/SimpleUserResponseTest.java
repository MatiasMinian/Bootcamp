package com.bootcamp.app.model.responses;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleUserResponseTest {

	SimpleUserResponse simpleUser = new SimpleUserResponse();
	Calendar lastLogin = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		lastLogin.set(2011, 2, 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserIsDeleteable() {
		assertTrue(simpleUser.isDeleteable(lastLogin));
	}
}