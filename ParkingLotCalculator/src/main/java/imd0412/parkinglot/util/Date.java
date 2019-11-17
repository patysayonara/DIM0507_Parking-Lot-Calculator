package imd0412.parkinglot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;
import imd0412.parkinglot.exception.InvalidDataType;

public class Date {

	static String DATE_EXCEPTION_MESSAGE = "Erro ao extrair data.";
	
	/**
	 * Validate a date as a string based in a DateTimeFormatter template.
	 * 
	 * @throws 
	 * 
	 * @param checkin
	 *            String representing check-in date. String follows the format
	 *            "yyyy.MM.dd HH:mm".
	 * @param template
	 * 			  DateTimeFormatter instance holding a date template
	 * @return LocalDateTime object if the date are well formatted
	 */
	public static LocalDateTime dateValidator(String text, DateTimeFormatter formatter){
		LocalDateTime date = null;
		try {
			date = LocalDateTime.parse(text, formatter);
		} catch(DateTimeParseException e) {
			throw new DateFormatException(DATE_EXCEPTION_MESSAGE);
		}
		
		int year = Integer.parseInt(text.substring(0,4));
		int month = Integer.parseInt(text.substring(5,7));
		int day = Integer.parseInt(text.substring(8,10));
		
		if(!verifyYear(year)) {
			throw new InvalidDataException(DATE_EXCEPTION_MESSAGE, InvalidDataType.InvalidYear);
		} 
		else if(!verifyMonth(month)) {
			throw new InvalidDataException(DATE_EXCEPTION_MESSAGE, InvalidDataType.InvalidMonth);
		} 
		else if(!verifyDay(day, month, isLeapYear(year))) {
			throw new InvalidDataException(DATE_EXCEPTION_MESSAGE, InvalidDataType.InvalidDay);
		}
		
		return date;
	}
	
	private static Boolean isLeapYear(int year) {
		if((year%4 == 0) && (year%100 != 0) || (year%400 == 0)) {
			return true;
		}
		return false;
	}
	
	private static Boolean verifyDay(int day, int month, Boolean leapYear) {
		if(day < 1 || day > 31) {
			return false;
		}
		else if(month == 2) {
			if(!leapYear && day > 28) {
				return false;
			}
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11) {
			if(day > 30) {
				return false;
			}
		}
		return true;
	}
	
	private static Boolean verifyMonth(int month) {
		if(month < 1 || month > 12) {
			return false;
		}
		return true;
	}
	
	private static Boolean verifyYear(int year) {
		if(year < 1970) {
			return false;
		}
		return true;
	}
}
