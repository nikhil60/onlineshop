package com.retail.onlineshop.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.onlineshop.dto.OrderDTO;
import com.retail.onlineshop.exception.OrderNotFoundException;
import com.retail.onlineshop.model.Cart;
import com.retail.onlineshop.model.Coupon;
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
		cart.setLastUpdationDate(new Date());
		cart.setCartPrice(calculateCartPrice(orderDTO.getCartId()));
		cartService.updateCart(cart,orderDTO.getCartId());
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
			cart.setLastUpdationDate(new Date());
			cart.setCartPrice(calculateCartPrice(orderDTO.getCartId()));
			cartService.updateCart(cart,orderDTO.getCartId());
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
		OrderDTO orderDTO = getOrder(orderId);
		Cart cart = cartService.viewCart(orderDTO.getCartId());
		orderRepository.deleteById(orderId);
		cart.setLastUpdationDate(new Date());
		cart.setCartPrice(calculateCartPrice(orderDTO.getCartId()));
		Set<Coupon> couponSet = cart.getCouponSet();
		Iterator<Coupon> couponIterator = couponSet.iterator();
		while(couponIterator.hasNext())
		{
			Coupon coupon = couponIterator.next();
			BigDecimal cartPrice = calculateCartPrice(orderDTO.getCartId());
			if(cartPrice.compareTo(coupon.getCouponValidPrice())==-1)
			{
				cart.removeCoupon(coupon);
				cart.setCartDiscount(cart.getCartDiscount().subtract(coupon.getCouponDiscount()));
				cartService.calculateTotal(cart);
			}
		}
		cartService.updateCart(cart,orderDTO.getCartId());
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
	
	public BigDecimal calculateCartPrice(Integer cartId)
	{
		BigDecimal totalCartPrice;
		totalCartPrice = new BigDecimal("00.00");
		Cart cart = cartService.viewCart(cartId);
		List<Order> orderList = orderRepository.findByCart(cart);
		Iterator<Order> iterator = orderList.iterator();
		while(iterator.hasNext())
		{
			Order order = iterator.next();
			totalCartPrice = totalCartPrice.add(order.getOrderPrice());
		}
		return totalCartPrice;
	}

}
