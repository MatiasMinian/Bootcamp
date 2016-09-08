package com.bootcamp.app.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DatesUtil {
	
	public static String formatDate(Calendar date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date.getTime());		
	}
	
	public static int differenceInDays(Calendar calendar1, Calendar calendar2) {
		LocalDate date1 = LocalDate.fromCalendarFields(calendar1);
		LocalDate date2 = LocalDate.fromCalendarFields(calendar2);
		return Days.daysBetween(date1, date2).getDays();
	}
}
