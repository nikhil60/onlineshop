package com.retail.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.onlineshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
