package com.retail.onlineshop.exception;

public class CouponNotFoundException extends RuntimeException {

	public CouponNotFoundException(String message)
	{
		super(message);
	}
}
