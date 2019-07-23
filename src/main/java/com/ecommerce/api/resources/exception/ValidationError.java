package com.ecommerce.api.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	/**
	 * 
	 * @param status
	 * @param msg
	 * @param timeStamp
	 */
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	
	}

	/**
	 * 
	 * @return
	 */
	public List<FieldMessage> getErrors() {
		return errors;
	}

	/**
	 * 
	 * @param fieldName
	 * @param message
	 */
	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message)); 
	}
}
