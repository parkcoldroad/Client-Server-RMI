package exception;

public class IllegalValueIdException extends Exception {
	private static final long serialVersionUID = 1L;

	public IllegalValueIdException(String errorMessage) {
		super(errorMessage);
	}
}
