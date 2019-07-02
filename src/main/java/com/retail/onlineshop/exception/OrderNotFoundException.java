package com.retail.onlineshop.exception;

public class OrderNotFoundException extends RuntimeException {
	
	public OrderNotFoundException(String message)
	{
		super(message);
	}
}
