package com.retail.onlineshop.service;

import java.math.BigDecimal;
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
		calculateTotal(cart);
		return cartRepository.save(cart);
	}
	
	public Cart updateCart(Cart cart,Integer cartId)
	{
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if(optionalCart.isPresent())
		{
			calculateTotal(cart);
			Cart updatedCart = cartRepository.save(cart);
			return updatedCart;
		}
		else
			return null;
	}
	
	public Cart calculateTotal(Cart cart)
	{
		BigDecimal cartTax = new BigDecimal("00.00");
		BigDecimal discountedPrice = new BigDecimal("00.00");
		BigDecimal cartTotal = new BigDecimal("00.00");
		discountedPrice=cart.getCartPrice().subtract(cart.getCartDiscount()==null?new BigDecimal("00.00"):cart.getCartDiscount());
		cartTax = discountedPrice.multiply(new BigDecimal("0.12"));
		cartTotal=cart.getCartPrice().subtract(cart.getCartDiscount()==null?new BigDecimal("00.00"):cart.getCartDiscount()).add(cartTax==null?new BigDecimal("00.00"):cartTax);
		cart.setCartTax(cartTax);
		cart.setCartTotal(cartTotal);
		return cart;
	}
}
