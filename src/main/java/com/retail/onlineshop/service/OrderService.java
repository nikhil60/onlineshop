package com.retail.onlineshop.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.retail.onlineshop.dto.OrderDTO;
import com.retail.onlineshop.exception.OrderNotFoundException;
import com.retail.onlineshop.model.Cart;
import com.retail.onlineshop.model.Order;
import com.retail.onlineshop.model.Product;
import com.retail.onlineshop.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	public OrderDTO saveOrder(OrderDTO orderDTO)
	{	
		Cart cart = cartService.viewCart(orderDTO.getCartId());
		Product product = productService.getProduct(orderDTO.getProductId());
		Order order = Order.convertToEntity(orderDTO, product, cart);
		Order savedOrder = orderRepository.save(order);
		OrderDTO savedOrderDTO = OrderDTO.convertToDTO(savedOrder);
		return savedOrderDTO;
	}
	
	public void updateOrder(OrderDTO orderDTO,Integer orderId)
	{
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isPresent())
		{
			Cart cart = cartService.viewCart(orderDTO.getCartId());
			Product product = productService.getProduct(orderDTO.getProductId());
			Order order = Order.convertToEntity(orderDTO, product, cart);
			orderRepository.save(order);
		}
		else
			throw new OrderNotFoundException("Order with Id "+ orderId + "not found!");
	}
	
	public List<OrderDTO> getAllOrderForCart(Integer cartId)
	{
		Cart cart = cartService.viewCart(cartId);
		List<Order> orderList = orderRepository.findByCart(cart);
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
		Iterator<Order> iterator = orderList.iterator();
		while(iterator.hasNext())
		{
			orderDTOList.add(OrderDTO.convertToDTO((Order) iterator.next()));
		}
		return orderDTOList;
	}
	
	public void deleteOrder(Integer orderId)
	{
		orderRepository.deleteById(orderId);
	}
	
	public OrderDTO getOrder(Integer orderId)
	{
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isPresent())
		{
			Order order = optionalOrder.get();
			OrderDTO orderDTO = OrderDTO.convertToDTO(order);
			return orderDTO;
		}
		else
			throw new OrderNotFoundException("Order with Id "+ orderId + "not found!");
	}

}
