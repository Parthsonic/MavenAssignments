package com.phoenix.exceptions;

public class ProductNotFoundException extends Exception{
	
	private String errorMessage;

	public ProductNotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}