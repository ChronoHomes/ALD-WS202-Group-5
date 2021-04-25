package A03_DoubleLinkedList;

public class CurrentNotSetException extends Exception {

	private static final long serialVersionUID = 251858618L;

	public CurrentNotSetException() {
	}

	public CurrentNotSetException(String message) {
		super(message);
	}

	public CurrentNotSetException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrentNotSetException(Throwable cause) {
		super(cause);
	}

	public CurrentNotSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
