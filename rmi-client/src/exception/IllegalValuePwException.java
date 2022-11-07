package exception;

public class IllegalValuePwException extends Exception {
	private static final long serialVersionUID = 1L;

	public IllegalValuePwException(String errorMessage) {
		super(errorMessage);
	}
}
