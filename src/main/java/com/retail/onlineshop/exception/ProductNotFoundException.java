package com.retail.onlineshop.exception;

public class ProductNotFoundException extends RuntimeException {
	
	public ProductNotFoundException(String message)
	{
		super(message);
	}
}
