package com.ecommerce.api.services.exceptions;

public class DataIntegrityException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param msg
	 */
	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	/**
	 * 
	 * @param msg
	 * @param cause
	 */
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
