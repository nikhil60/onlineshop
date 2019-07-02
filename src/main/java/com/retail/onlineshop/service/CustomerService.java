package com.retail.onlineshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.onlineshop.exception.CustomerNotFoundException;
import com.retail.onlineshop.model.Cart;
import com.retail.onlineshop.model.Customer;
import com.retail.onlineshop.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
		
	public Customer saveCustomer(Customer customer)
	{
		Cart cart = new Cart();
		cart.setCreationDate(new Date());
		customer.setCart(cart);
		Customer savedCustomer  = customerRepository.save(customer);
		return savedCustomer;
	}
	
	public void updateCustomer(Customer customer)
	{
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());
		if(optionalCustomer.isPresent())
			customerRepository.save(customer);
		else
			throw new CustomerNotFoundException("Customer Id "+customer.getCustomerId()+" Not Found");
	}
	
	public void removeCustomer(Integer customerId)
	{
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if(optionalCustomer.isPresent())
			customerRepository.delete(optionalCustomer.get());
		else
			throw new CustomerNotFoundException("Customer Id "+customerId+" Not Found");
	}
	
	public List<Customer> getAllCustomer()
	{
		return customerRepository.findAll();
	}
	
	public Customer getCustomer(Integer customerId)
	{
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if(optionalCustomer.isPresent())
			return optionalCustomer.get();
		else
			throw new CustomerNotFoundException("Customer Id "+customerId+" Not Found");
	}
}
