package com.retail.onlineshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.onlineshop.model.Cart;
import com.retail.onlineshop.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	public Cart viewCart(Integer cartId)
	{
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if(optionalCart.isPresent())
			return optionalCart.get();
		else
			return null;
	}
	
	public Cart saveCart(Cart cart)
	{
		return cartRepository.save(cart);
	}
	
	public Cart updateCart(Cart cart,Integer cartId)
	{
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if(optionalCart.isPresent())
		{
			Cart updatedCart = cartRepository.save(cart);
			return updatedCart;
		}
		else
			return null;
	}
}
