package exception;

public class DuplicateUserIdException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateUserIdException(String errorMessage) {
		super(errorMessage);
	}
}
