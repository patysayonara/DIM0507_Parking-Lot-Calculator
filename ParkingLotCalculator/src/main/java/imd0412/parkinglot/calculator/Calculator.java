package imd0412.parkinglot.calculator;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import imd0412.parkinglot.Constants;
import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.util.Date;


public class Calculator {
	
	static String CALCULATEPARKINGCOST_ERROR_MESSAGE = "Erro ao calcular o custo.";
	
	/**
	 * Calculates the staying cost in the parking lot.
	 * 
	 * @param checkin
	 *            String representing check-in date. String follows the format
	 *            "yyyy.MM.dd HH:mm".
	 * @param checkout
	 *            String representing check-out date. String follows the format
	 *            "yyyy.MM.dd HH:mm".
	 * @param type
	 * @return 
	 */
	Float calculateParkingCost(String checkin, String checkout,
			ParkingLotType type) {
		
		LocalDateTime checkinTime = null;
		LocalDateTime checkoutTime = null;
		
		try {
			checkinTime = Date.dateValidator(checkin, Constants.DATE_FORMATTER);
			checkoutTime = Date.dateValidator(checkout, Constants.DATE_FORMATTER);
		}catch (DateTimeParseException e){
			throw new DateFormatException(CALCULATEPARKINGCOST_ERROR_MESSAGE); // Fiz esse catch só pra modificar a mensagem de erro
		}
		
		// TODO Auto-generated method stub
		return null;
	}
}
