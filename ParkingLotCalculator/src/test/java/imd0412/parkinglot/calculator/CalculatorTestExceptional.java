package imd0412.parkinglot.calculator;

import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;
import imd0412.parkinglot.calculator.Calculator;
import static imd0412.parkinglot.calculator.Calculator.CALCULATEPARKINGCOST_ERROR_MESSAGE;
import static imd0412.parkinglot.ParkingLotType.ShortTerm;

@RunWith(Parameterized.class)
public class CalculatorTestExceptional {

	@Parameters(name = "{0}_to_{1}-{3}")
	public static Collection<Object[]> buildData() {
		return Arrays.asList(new Object[][] {
				{ "2010.10.10 10:10", "2010.10.10 10:09", InvalidDataException.class, "B1-Action12"},
				{ "2019.10.10 10:10", "2010.10.10 10:10", InvalidDataException.class, "C1-Action12"},
				{ "2010.10.10 10:10", "2020.10.10 10:10", InvalidDataException.class, "D1-Action12"},
				{ "2010.02.29 10:10", "2010.02.30 10:10", InvalidDataException.class, "E2-Action12"},
				{ "10:10 20/07/1969", "10:10 20/07/1970", DateFormatException.class, "E3-Action13"}
		});
	}
	
	@Rule
	public ExpectedException e = ExpectedException.none();
	
	@Parameter(0)
	public String checkin;

	@Parameter(1)
	public String checkout;
	
	@Parameter(2)
	public Exception expectedException;
	
	@Parameter(3)
	public String testName;

	
	@Test
	public void testCalculateParkingCost() {
		e.expect((Matcher<?>) expectedException);
		e.expectMessage(CALCULATEPARKINGCOST_ERROR_MESSAGE);
		
		Calculator calculator = new Calculator();
		calculator.calculateParkingCost(checkin, checkout, ShortTerm);
	}

}
