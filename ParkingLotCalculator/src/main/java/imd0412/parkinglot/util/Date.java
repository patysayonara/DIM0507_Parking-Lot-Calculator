package imd0412.parkinglot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	 * @return True if the date are well formatted
	 */
	public static LocalDateTime dateValidator(String text, DateTimeFormatter formatter) {
		// TODO
		return null;
	}
}
