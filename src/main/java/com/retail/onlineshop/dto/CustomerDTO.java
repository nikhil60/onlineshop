package com.retail.onlineshop.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.retail.onlineshop.model.Customer;

public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer customerId;
	@NotNull
	private String userName;
	@NotNull
	@Size(min=8,max=16)
	private String passWord;
	@NotNull
	private String firstName;
	@NotNull
	private String middleName;
	@NotNull
	private String lastName;
	@Email
	@NotNull
	private String emailAddress;
	@NotNull
	private Date dateOfBirth;
	@NotNull
	private String gender;
	@NotNull
	private String maritalStatus;
	
	public CustomerDTO()
	{
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static CustomerDTO converToDTO(Customer customer)
	{
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setUserName(customer.getUserName());
		customerDTO.setPassWord(customer.getPassWord());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setMiddleName(customer.getMiddleName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setEmailAddress(customer.getEmailAddress());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setGender(customer.getGender());
		customerDTO.setMaritalStatus(customer.getMaritalStatus());
		
		return customerDTO;
	}
}
