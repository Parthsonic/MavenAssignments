package com.phoenix.exceptions;

public class UserAlreadyExistException extends Exception{
	
	private String errorMessage;

	public UserAlreadyExistException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}	

