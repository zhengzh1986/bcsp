package com.sf.bcsp.exception;

public class DataAcessException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public DataAcessException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public DataAcessException(String message){
		super(message);
	}

}
