package com.retail.onlineshop.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String message)
	{
		super(message);
	}
}
