package com.retail.onlineshop.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler 
{
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> customhandleException(Exception ex, WebRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimestamp(LocalDateTime.now());
		exceptionResponse.setError(ex.getMessage());
		exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.NOT_FOUND);
	}
}
