package com.phoenix.exceptions;

public class UserNotFoundException extends Exception{
	
	private String errorMessage;

	public UserNotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}	
