package lms.exception;

public class DuplicateEnrollmentException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateEnrollmentException(String errorMessage) {
		super(errorMessage);
	}
}
