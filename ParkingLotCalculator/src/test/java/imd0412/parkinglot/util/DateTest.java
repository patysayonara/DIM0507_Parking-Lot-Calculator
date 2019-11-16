package imd0412.parkinglot.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import imd0412.parkinglot.Constants;

public class DateTest {

	@Test
	public void testDateValidator() {
		LocalDateTime date = Date.dateValidator("2010.10.10 10:10", Constants.DATE_FORMATTER);
		
		int year = date.getYear();
		int month = date.getMonth().getValue();
		int dayOfMonth = date.getDayOfMonth();
		int hour = date.getHour();
		int minute = date.getMinute();
		
		assertEquals(year, 2010);
		assertEquals(month, 10);
		assertEquals(dayOfMonth, 10);
		assertEquals(hour, 10);
		assertEquals(minute, 10);
	}
}
