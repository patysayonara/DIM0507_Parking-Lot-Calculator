package imd0412.parkinglot.calculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.ParkingLotType;
import static imd0412.parkinglot.ParkingLotType.ShortTerm;
import static imd0412.parkinglot.ParkingLotType.LongTerm;
import static imd0412.parkinglot.ParkingLotType.VIP;

@RunWith(Parameterized.class)
public class CalculatorTest
{
	
	@Parameters(name = "{0}_to_{1}_should_cost_{2}-{4}")
	public static Collection<Object[]> buildData() {
		return Arrays.asList(new Object[][] {
			{"2010.10.10 10:10", "2010.10.10 10:11", 8F, ShortTerm},// {1}, 1min
			{"2010.10.10 10:10", "2010.10.10 10:40", 8F, ShortTerm},// {30}, 30min
			{"2010.10.10 10:10", "2010.10.10 11:11", 10F, ShortTerm},// {61}, 1h1min
			{"2010.10.10 10:10", "2010.10.10 12:11", 14F, ShortTerm},// {121}, 2h1min
			{"2010.10.10 10:10", "2010.10.11 10:10", 54F, ShortTerm},// {1440}, 1dia
			{"2010.10.10 10:10", "2010.10.11 11:10", 106F, ShortTerm},// {1500}, 1dia1hora
			{"2010.10.10 10:10", "2010.10.17 10:10", 642F, ShortTerm},// {10080}, 7dias
			{"2010.10.10 10:10", "2010.10.17 11:10", 524F, ShortTerm},// {10140}, 7dias1hora
			
			{"2010.10.10 10:10", "2010.10.10 11:11", 70F, LongTerm},// {61}, 1h1min
			{"2010.10.10 10:10", "2010.10.10 12:11", 70F, LongTerm},// {121}, 2h1min
			{"2010.10.10 10:10", "2010.10.11 10:10", 70F, LongTerm},// {1440}, 1dia
			{"2010.10.10 10:10", "2010.10.11 11:10", 120F, LongTerm},// {1500}, 1dia1hora
			{"2010.10.10 10:10", "2010.10.17 10:10", 370F, LongTerm},// {10080}, 7dias
			{"2010.10.10 10:10", "2010.10.17 11:10", 280F, LongTerm},// {10140}, 7dias1hora
			{"2010.10.10 10:10", "2010.11.10 10:10", 1470F, LongTerm},// {43200}, 30dias 
			{"2010.10.10 10:10", "2010.11.10 11:10", 1500F, LongTerm},// {43260}, 30dias1h
			
			{"2010.10.10 10:10", "2010.10.11 10:10", 500F, VIP},// {1440}, 1dia
			{"2010.10.10 10:10", "2010.10.11 11:10", 500F, VIP},// {1500}, 1dia1hora
			{"2010.10.10 10:10", "2010.10.17 10:10", 500F, VIP},// {10080}, 7dias
			{"2010.10.10 10:10", "2010.10.17 11:10", 600F, VIP},// {10140}, 7dias1hora
			{"2010.10.10 10:10", "2010.10.24 10:10", 1200F, VIP},// {20160}, 14dias
			{"2010.10.10 10:10", "2010.10.24 11:10", 1140F, VIP},// {20220}, 14dias1h
		});
	}
	
	@Parameter(0)
	public String checkin;

	@Parameter(1)
	public String checkout;

	@Parameter(2)
	public Float expectedCost;
	
	@Parameter(3)
	public ParkingLotType type;
	
	@Parameter(4)
	public String testName;
	
	@Test
	public void testcalculateParkingCost() {
		Calculator calculator = new Calculator();
		
		Float actualCost = calculator.calculateParkingCost(checkin, checkout, type);
		
		assertThat(actualCost, is(equalTo(expectedCost)));
	}
}
	


