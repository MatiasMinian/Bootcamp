package com.bootcamp.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GroupTest {

	Group javaGroup = new Group("javaGroup");
	
	User matias = new User("matias", "minian.matias@gmail.com");
	
	@Before
	public void setUp() throws Exception {
		javaGroup.subscribeUser(matias);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserWasAdded() {
		assertTrue(javaGroup.getSubscribers().contains(matias));
	}

}
