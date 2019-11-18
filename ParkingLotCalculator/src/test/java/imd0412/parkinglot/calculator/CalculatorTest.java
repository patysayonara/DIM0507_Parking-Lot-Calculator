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
			{"2010.10.10 10:10", "2010.10.10 10:10", 0F, ShortTerm, "B2C2D2E1-Action1"},// {0min}, 0min
			{"2010.10.10 10:10", "2010.10.10 10:11", 8F, ShortTerm, "A1B3minC2D2E1-Action2"},// {1min}, 1min
			{"2010.10.10 10:10", "2010.10.10 10:40", 8F, ShortTerm, "A1B3C2D2E1-Action2"},// {30min}, 30min
			{"2010.10.10 10:10", "2010.10.10 11:11", 10F, ShortTerm, "A1B4minC2D2E1-Action3"},// {61min}, 1h1min
			{"2010.10.10 10:10", "2010.10.10 12:11", 12F, ShortTerm, "A1B4C2D2E1-Action3"},// {121min}, 2h1min
			{"2010.10.10 10:10", "2010.10.11 10:10", 54F, ShortTerm, "A1B5minC2D2E1-Action3"},// {1440min}, 1dia
			{"2010.10.10 10:10", "2010.10.11 11:10", 106F, ShortTerm, "A1B5C2D2E1-Action4"},// {1500min}, 1dia1hora
			{"2010.10.10 10:10", "2010.10.17 10:10", 692F, ShortTerm, "A1B6minC2D2E1-Action4"},// {10080min}, 7dias
			{"2010.10.10 10:10", "2010.10.17 11:10", 554F, ShortTerm, "A1B6C2D2E1-Action5"},// {10140min}, 7dias1hora
			{"2010.10.10 10:10", "2010.10.10 10:10", 0F, LongTerm, "A2B2C2D2E1-Action1"},// {0min}, 0min
			{"2010.10.10 10:10", "2010.10.10 11:11", 70F, LongTerm, "A2B4minC2D2E1-Action6"},// {61min}, 1h1min
			{"2010.10.10 10:10", "2010.10.10 12:11", 70F, LongTerm, "A2B4C2D2E1-Action6"},// {121min}, 2h1min
			{"2010.10.10 10:10", "2010.10.11 10:10", 70F, LongTerm, "A2B5minC2D2E1-Action6"},// {1440min}, 1dia
			{"2010.10.10 10:10", "2010.10.11 11:10", 120F, LongTerm, "A2B5C2D2E1-Action7"},// {1500min}, 1dia1hora
			{"2010.10.10 10:10", "2010.10.17 10:10", 370F, LongTerm, "A2B6minC2D2E1-Action7"},// {10080min}, 7dias
			{"2010.10.10 10:10", "2010.10.17 11:10", 280F, LongTerm, "A2B6C2D2E1-Action8"},// {10140min}, 7dias1hora
			{"2010.10.10 10:10", "2010.11.09 10:10", 1440F, LongTerm, "A2B8minC2D2E1-Action9"},// {43200min}, 30dias 
			{"2010.10.10 10:10", "2010.11.09 11:10", 1470F, LongTerm, "A2B8C2D2E1-Action9"},// {43260min}, 30dias1h
			{"2010.10.10 10:10", "2010.10.10 10:10", 0F, VIP, "A3B2C2D2E1-Action1"},// {0min}, 0min
			{"2010.10.10 10:10", "2010.10.11 10:10", 500F, VIP, "A3B5minC2D2E1-Action10"},// {1440min}, 1dia
			{"2010.10.10 10:10", "2010.10.11 11:10", 500F, VIP, "A3B5C2D2E1-Action10"},// {1500min}, 1dia1hora
			{"2010.10.10 10:10", "2010.10.17 10:10", 500F, VIP, "A3B6minC2D2E1-Action10"},// {10080min}, 7dias
			{"2010.10.10 10:10", "2010.10.18 10:10", 600F, VIP, "A3B6C2D2E1-Action11"},// {11520min}, 8dias
			{"2010.10.10 10:10", "2010.10.24 10:10", 1200F, VIP, "A3B7minC2D2E1-Action12"},// {20160min}, 14dias
			{"2010.10.10 10:10", "2010.10.25 10:10", 1140F, VIP, "A3B7C2D2E1-Action12"},// {21600min}, 15dias
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
	


