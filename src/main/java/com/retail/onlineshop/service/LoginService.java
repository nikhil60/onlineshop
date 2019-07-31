package com.retail.onlineshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.onlineshop.dto.DefaultResponseDTO;
import com.retail.onlineshop.exception.CustomerNotFoundException;
import com.retail.onlineshop.exception.InvalidLoginException;
import com.retail.onlineshop.model.Customer;
import com.retail.onlineshop.repository.CustomerRepository;

@Service
public class LoginService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer validateLogin(String userName,String passWord)
	{
		Optional<Customer> optionalCustomer = customerRepository.findByuserNameAndpassWord(userName, passWord);
		if(optionalCustomer.isPresent())
		{
			Customer customer = optionalCustomer.get();
			customer.setLoginStatus(true);
			customerRepository.save(customer);
			return customer;
		}
		else
			throw new InvalidLoginException("Invalid Login Credentials!");
	}
	
	public DefaultResponseDTO logout(String userName)
	{
		Optional<Customer> optionalCustomer = customerRepository.findByuserName(userName);
		if(optionalCustomer.isPresent())
		{
			Customer customer = optionalCustomer.get();
			customer.setLoginStatus(false);
			customerRepository.save(customer);
			DefaultResponseDTO responseDTO = new DefaultResponseDTO("Logged out successfully!");
			return responseDTO;
		}
		else
			throw new CustomerNotFoundException("Invalid UserName!");
	}
}
