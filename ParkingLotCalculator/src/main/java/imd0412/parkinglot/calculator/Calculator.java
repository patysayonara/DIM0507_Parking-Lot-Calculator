package imd0412.parkinglot.calculator;

import java.time.Duration;
import java.time.LocalDateTime;

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
			throw new DateFormatException(CALCULATEPARKINGCOST_ERROR_MESSAGE);
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
			return 8F;
		}
		else if(minutes >= 61 && minutes <= 1440) {
			return (float) (8+2*(Math.ceil(minutes/60F)-1));
		}
		else if(minutes > 1440 && minutes <= 10080) {
			return (float) (8+2*(Math.ceil(minutes/60F)-1)+50*(days));
		}
		else if(minutes > 10080) {
			return (float) (8+2*(Math.ceil(minutes/60F)-1)+30*(days));
		}
		
		return 0F;
	}
	
	private Float calculateLongTermCost(Duration duration) {
		long minutes = duration.toMinutes();
		long days = duration.toDays();
		
		if(minutes >= 1 && minutes <= 1440) {
			return 70F;
		}
		else if(minutes > 1440 && minutes <= 10080) {
			return (float) (70+(Math.ceil(minutes/1440F)-1)*50);
		}
		else if(minutes > 10080 && minutes < 43200) {
			return (float) (70+(Math.ceil(minutes/1440F)-1)*30);
		}
		else if(minutes >= 43200) {
			return (float) (70+(Math.ceil(minutes/1440F)-1)*30 + 500*Math.floor(days/30F));
		}
		
		return 0F;
	}
	
	private Float calculateVIPCost(Duration duration) {
		long minutes = duration.toMinutes();
		long days = duration.toDays();
		
		if(minutes >= 1 && minutes <= 10080) {
			return 500F;
		}
		else if(minutes > 10080 && minutes <= 20160) {
			return (float) (500+(days-7)*100);
		}
		else if(minutes > 20160) {
			return (float) (500+(days-7)*80);
		}
		
		return 0F;
	}
}
