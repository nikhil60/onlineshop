package com.retail.onlineshop.exception;

public class InvalidLoginException extends RuntimeException {
	
	public InvalidLoginException(String message)
	{
		super(message);
	}

}
