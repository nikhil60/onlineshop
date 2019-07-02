package com.retail.onlineshop.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.retail.onlineshop.dto.CustomerDTO;
import com.retail.onlineshop.model.Cart;
import com.retail.onlineshop.model.Customer;
import com.retail.onlineshop.service.CustomerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping(path="/customers")
	public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO)
	{
		Customer customer = Customer.convertToEntity(customerDTO);
		
		Customer savedCustomer = customerService.saveCustomer(customer);
		
		CustomerDTO savedCustomerDTO = CustomerDTO.converToDTO(savedCustomer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedCustomerDTO.getCustomerId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path="/customers/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody @Valid CustomerDTO customerDTO,@PathVariable("id") Integer CustomerId)
	{
		Customer customer = Customer.convertToEntity(customerDTO);
		
		customerService.updateCustomer(customer);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path="/customers/{id}")
	public void removeCustomer(@PathVariable("id") Integer CustomerId)
	{
		customerService.removeCustomer(CustomerId);
	}
	
	@GetMapping(path="/customers")
	public List<CustomerDTO> getAllCustomers()
	{
		List<Customer> customerList = customerService.getAllCustomer();
		List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
		java.util.Iterator<Customer> iterator = customerList.iterator();
		while(iterator.hasNext())
		{
			customerDTOList.add(CustomerDTO.converToDTO(iterator.next())); 
		}
		return customerDTOList;
	}
	
	@GetMapping(path="/customers/{id}")
	public CustomerDTO getCustomerById(@PathVariable("id") Integer CustomerId)
	{
		Customer customer = customerService.getCustomer(CustomerId);
		return CustomerDTO.converToDTO(customer);
	}
}
