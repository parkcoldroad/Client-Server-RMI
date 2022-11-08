package exception;

public class DuplicatedCourseIdException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicatedCourseIdException(String errorMessage) {
		super(errorMessage);
	}
}
