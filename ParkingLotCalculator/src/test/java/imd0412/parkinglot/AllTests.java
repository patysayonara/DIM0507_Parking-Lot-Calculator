package imd0412.parkinglot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import imd0412.parkinglot.calculator.CalculatorTest;
import imd0412.parkinglot.calculator.CalculatorTestExceptional;
import imd0412.parkinglot.util.DateTest;
import imd0412.parkinglot.util.DateTestExceptional;

@RunWith(Suite.class)
@SuiteClasses(value = {	CalculatorTest.class,
		CalculatorTestExceptional.class,
		DateTest.class,
		DateTestExceptional.class})
public class AllTests {

}
