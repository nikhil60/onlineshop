package com.retail.onlineshop.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.retail.onlineshop.dto.CustomerDTO;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private Integer customerId;
	private String userName;
	private String passWord;
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailAddress;
	private Date dateOfBirth;
	private String gender;
	private String maritalStatus;
	private Boolean loginStatus;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CART_ID")
	private Cart cart;
	
	protected Customer()
	{}

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

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	public static Customer convertToEntity(CustomerDTO customerDTO)
	{
		Customer customer = new Customer();
		
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setUserName(customerDTO.getUserName());
		customer.setPassWord(customerDTO.getPassWord());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setMiddleName(customerDTO.getMiddleName());
		customer.setLastName(customerDTO.getLastName());
		customer.setEmailAddress(customerDTO.getEmailAddress());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setGender(customerDTO.getGender());
		customer.setMaritalStatus(customerDTO.getMaritalStatus());
		
		return customer;
	}
	
}
