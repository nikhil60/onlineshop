package com.retail.onlineshop.exception;

public class CartNotFoundException extends RuntimeException{
	
	public CartNotFoundException(String message)
	{
		super(message);
	}

}
