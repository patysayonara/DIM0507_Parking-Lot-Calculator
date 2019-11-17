package imd0412.parkinglot.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.Test;

import imd0412.parkinglot.Constants;
import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;

public class DateTest {

	@Test
	public void testDateValidator() {
		LocalDateTime date = null;
		try {
			date = Date.dateValidator("2010.10.10 10:10", Constants.DATE_FORMATTER);
		} catch(DateTimeParseException e){
		} catch(InvalidDataException e) {
		}
		
		System.out.println(date);
		
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
