package com.retail.onlineshop.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.onlineshop.model.Cart;
import com.retail.onlineshop.model.Order;
import com.retail.onlineshop.model.Product;

public interface OrderRepository extends JpaRepository<Order,Integer>{

	public List<Order> findByCart(Cart cart);
	
	public Optional<Order> findByProduct(Product product);
}
