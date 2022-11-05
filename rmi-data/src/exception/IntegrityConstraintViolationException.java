package exception;

public class IntegrityConstraintViolationException extends Exception {
	private static final long serialVersionUID = 1L;

	public IntegrityConstraintViolationException(String errorMessage) {
		super(errorMessage);
	}
}
