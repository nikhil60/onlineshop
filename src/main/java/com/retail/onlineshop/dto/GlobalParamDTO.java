package com.retail.onlineshop.dto;

import java.io.Serializable;

import com.retail.onlineshop.model.Customer;

public class GlobalParamDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer customerId;
	private String userName;
	private Boolean loginStatus;
	private Integer cartId;
	private Boolean success;
	
	public GlobalParamDTO() {
		super();
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public static GlobalParamDTO convertToDTO(Customer customer)
	{
		GlobalParamDTO globalParamDTO = new GlobalParamDTO();
		
		globalParamDTO.setCartId(customer.getCart().getCartId());
		globalParamDTO.setCustomerId(customer.getCustomerId());
		globalParamDTO.setLoginStatus(customer.getLoginStatus());
		globalParamDTO.setUserName(customer.getUserName());
		globalParamDTO.setSuccess(true);
		return globalParamDTO;
	}

}
