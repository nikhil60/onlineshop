package com.retail.onlineshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retail.onlineshop.model.Cart;
import com.retail.onlineshop.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	@Query("select c from Customer c where c.userName=:userName and c.passWord=:passWord")
	public Optional<Customer> findByuserNameAndpassWord(@Param("userName") String userName,@Param("passWord") String passWord);
	
	@Query("select c.cart from Customer c where c.customerId=:customerId")
	public Optional<Cart> findCartByCustomer(@Param("customerId") Integer customerId);
}
