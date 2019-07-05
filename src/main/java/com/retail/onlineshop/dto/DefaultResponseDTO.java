package com.retail.onlineshop.dto;

import java.io.Serializable;

public class DefaultResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public DefaultResponseDTO() {
		
	}
	
	public DefaultResponseDTO(String message) {
		super();
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
