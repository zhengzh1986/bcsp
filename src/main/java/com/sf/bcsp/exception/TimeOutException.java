package com.sf.bcsp.exception;

public class TimeOutException extends Exception {

	private static final long serialVersionUID = 1L;

	public TimeOutException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeOutException(String message) {
		super(message);
	}

}
