package com.tennissetapp.exception;

@Deprecated //use DuplicateRecordException instead
public class AccountExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccountExistsException() {
		super();
	}

	public AccountExistsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountExistsException(String message) {
		super(message);
	}

	public AccountExistsException(Throwable cause) {
		super(cause);
	}
}
