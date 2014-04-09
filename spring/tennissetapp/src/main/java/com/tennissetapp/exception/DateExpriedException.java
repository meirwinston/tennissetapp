package com.tennissetapp.exception;

public class DateExpriedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DateExpriedException() {
		super();
	}

	public DateExpriedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DateExpriedException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateExpriedException(String message) {
		super(message);
	}

	public DateExpriedException(Throwable cause) {
		super(cause);
	}
}
