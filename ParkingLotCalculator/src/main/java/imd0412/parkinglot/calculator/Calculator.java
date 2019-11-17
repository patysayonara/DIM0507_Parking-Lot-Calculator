package imd0412.parkinglot.calculator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import imd0412.parkinglot.Constants;
import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;
import imd0412.parkinglot.exception.InvalidDataType;
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
	Float calculateParkingCost(String checkin, String checkout, ParkingLotType type) {
		LocalDateTime checkinTime = null;
		LocalDateTime checkoutTime = null;
		
		try {
			checkinTime = Date.dateValidator(checkin, Constants.DATE_FORMATTER);
			checkoutTime = Date.dateValidator(checkout, Constants.DATE_FORMATTER);
		} catch(DateFormatException e){
			throw new DateFormatException(CALCULATEPARKINGCOST_ERROR_MESSAGE); // Fiz esse catch só pra modificar a mensagem de erro
		} catch(InvalidDataException e) {
			throw new InvalidDataException(CALCULATEPARKINGCOST_ERROR_MESSAGE,e.getReason());
		}
				
		if(checkinTime.getYear() > 2018 || checkoutTime.getYear() > 2019) {
			throw new InvalidDataException(CALCULATEPARKINGCOST_ERROR_MESSAGE, InvalidDataType.InvalidYear);
		}
		
		Duration duration = Duration.between(checkinTime, checkoutTime);
		
		if(duration.toMinutes() < 0) {
			throw new InvalidDataException(CALCULATEPARKINGCOST_ERROR_MESSAGE, InvalidDataType.NonexistentDate);
		}
		
		Float totalCost = null;
		
		if(type.equals(ParkingLotType.ShortTerm)) {
			totalCost = calculateShortTermCost(duration);
		} 
		else if(type.equals(ParkingLotType.LongTerm)) {
			totalCost = calculateLongTermCost(duration);
		} 
		else if(type.equals(ParkingLotType.VIP)) {
			totalCost = calculateVIPCost(duration);
		}
			
		
		return totalCost;
	}
	
	private Float calculateShortTermCost(Duration duration) {
		long minutes = duration.toMinutes();
		long days = duration.toDays();
		
		if(minutes >= 1 && minutes < 61) {
			return 8.0f;
		}
		else if(minutes >= 61 && minutes <= 1440) {
			return (float) (8+2*(Math.ceil(minutes/60)-1));
		}
		else if(minutes > 1440 && minutes <= 10080) {
			return (float) (8+2*(Math.ceil(minutes/60)-1)+50*(days-1));
		}
		else if(minutes > 10080) {
			return (float) (8+2*(Math.ceil(minutes/60)-1)+30*(days-1));
		}
		
		return 0.0f;
	}
	
	private Float calculateLongTermCost(Duration duration) {
		long minutes = duration.toMinutes();
		long days = duration.toDays();
		
		if(minutes >= 1 && minutes <= 1440) {
			return 70.0f;
		}
		else if(minutes > 1440 && minutes <= 10080) {
			return (float) (70+(Math.ceil(minutes/1440))*50);
		}
		else if(minutes > 10080 && minutes <= 43200) {
			return (float) (70+(Math.ceil(minutes/1440))*30);
		}
		else if(minutes > 43200) {
			return (float) (70+(Math.ceil(minutes/1440))*30 + 500*Math.floor(days/30));
		}
		
		return 0.0f;
	}
	
	private Float calculateVIPCost(Duration duration) {
		long minutes = duration.toMinutes();
		long days = duration.toDays();
		
		if(minutes >= 1 && minutes <= 10080) {
			return 500.0f;
		}
		else if(minutes > 10080 && minutes <= 20160) {
			return (float) (500+(Math.ceil(minutes/1440))*100);
		}
		else if(minutes > 20160) {
			return (float) (500+(Math.ceil(minutes/1440))*80);
		}
		
		return 0.0f;
	}
}
