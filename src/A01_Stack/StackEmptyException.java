package A01_Stack;

public class StackEmptyException extends Exception {

	private static final long serialVersionUID = 52034036L;

	public StackEmptyException() {
	}

	public StackEmptyException(String message) {
		super(message);
	}

	public StackEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public StackEmptyException(Throwable cause) {
		super(cause);
	}

	public StackEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
