package com.phoenix.exceptions;

public class ProductIdAlreadyExistException extends Exception{
	
	private String errorMessage;
	public ProductIdAlreadyExistException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}
