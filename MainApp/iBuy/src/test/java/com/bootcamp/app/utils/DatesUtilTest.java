package com.bootcamp.app.utils;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatesUtilTest {
	
	Calendar date1 = Calendar.getInstance();
	Calendar date2 = Calendar.getInstance();
	

	@Before
	public void setUp() throws Exception {
		date1.set(1992, 10, 10);
		date2.set(1992, 10, 20);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormatDateMatchesGivenDate() {
		String formatedDate = DatesUtil.formatDate(date1);
		assertTrue(formatedDate.equals("1992-11-10"));
	}
	
	@Test
	public void testDifferenceInDaysBetweenTwoDatesIsCorrect() {
		int difference = DatesUtil.differenceInDays(date1, date2);
		assertTrue(difference == 10);
	}
	
	@Test
	public void testDaysWereAddedToGivenDate() {
		DatesUtil.addDays(date2, 5);
		assertTrue(date2.get(Calendar.DAY_OF_MONTH) == 25);
	}
}