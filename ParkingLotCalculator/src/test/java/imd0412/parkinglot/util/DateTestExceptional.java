package imd0412.parkinglot.util;

import java.time.format.DateTimeParseException;
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

import imd0412.parkinglot.Constants;
import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;

@RunWith(Parameterized.class)
public class DateTestExceptional {

	@Parameters(name = "{0}-{2}")
	public static Collection<Object[]> buildData() {
		return Arrays.asList(new Object[][] {
				{ "2010.02.29 10:10", InvalidDataException.class, "A1B1C1D2-Action2"},
				{ "2010.02.30 10:10", InvalidDataException.class, "A2B1C1-Action2"},
				{ "2010.02.31 10:10", InvalidDataException.class, "A3B1C1-Action2"},
				{ "2010.04.31 10:10", InvalidDataException.class, "A3B2C1-Action2"},
				{ "1969.07.20 10:10", InvalidDataException.class, "C2-Action2"},
				{ "10:10 20/07/1969", DateFormatException.class, "E2-Action3"}
		});
	}
	
	@Rule
	public ExpectedException e = ExpectedException.none();
	
	@Parameter(0)
	public String date;

	@Parameter(1)
	public Class<? extends Exception> expectedException;
	
	@Parameter(2)
	public String testName;
	
	@Test
	public void testNextDayInvalidFormat() {
		e.expect(expectedException);
		e.expectMessage(Date.DATE_EXCEPTION_MESSAGE);
	
		System.out.println(Date.dateValidator(date, Constants.DATE_FORMATTER));
	}

}
