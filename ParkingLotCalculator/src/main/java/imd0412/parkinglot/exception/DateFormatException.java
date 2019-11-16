package imd0412.parkinglot.exception;

public class DateFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DateFormatException() {
		super();
	}

	public DateFormatException(String msg) {
		super(msg);
	}
}
