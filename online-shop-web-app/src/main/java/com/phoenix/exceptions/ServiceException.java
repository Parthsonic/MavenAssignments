package com.phoenix.exceptions;

public class ServiceException extends Exception {
	
	private String errorMessage;

	public ServiceException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}
