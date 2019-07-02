package com.retail.onlineshop.controller;

import java.net.URI;
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

import com.retail.onlineshop.dto.OrderDTO;
import com.retail.onlineshop.service.OrderService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping(path="cart/{cartId}/orders/{orderId}")
	public OrderDTO getOrder(@PathVariable("cartId") Integer cartId,@PathVariable("orderId") Integer orderId)
	{
		return orderService.getOrder(orderId);
	}
	
	@GetMapping(path="cart/{cartId}/orders")
	public List<OrderDTO> getOrder(@PathVariable("cartId") Integer cartId)
	{
		return orderService.getAllOrderForCart(cartId);
	}
	
	@DeleteMapping(path="cart/{cartId}/orders/{orderId}")
	public void deleteOrder(@PathVariable("cartId") Integer cartId,@PathVariable("orderId") Integer orderId)
	{
		orderService.deleteOrder(orderId);
	}
	
	@PostMapping(path="/cart/{cartId}/orders")
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody @Valid OrderDTO orderDTO)
	{
		OrderDTO savedOrderDTO = orderService.saveOrder(orderDTO);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/orderId").buildAndExpand(savedOrderDTO.getOrderId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path="cart/{cartId}/orders/{orderId}")
	public ResponseEntity<OrderDTO> updateCustomer(@RequestBody @Valid OrderDTO orderDTO,@PathVariable("orderId") Integer orderId)
	{
		orderService.updateOrder(orderDTO,orderId);
		
		return ResponseEntity.noContent().build();
	}
}
