package com.retail.onlineshop.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.retail.onlineshop.model.Product;
import com.retail.onlineshop.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping(path="/products")
	public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product)
	{
		Product savedProduct = productService.saveProduct(product);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedProduct.getProductId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path="/products")
	public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product)
	{
		productService.updateProduct(product);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path="/products/{id}")
	public void removeProduct(@PathVariable("id") Integer productId)
	{
		productService.removeProduct(productId);
	}
	
	@GetMapping(path="/products")
	public List<Product> getAllProducts()
	{
		return productService.getAllProducts();
	}
	
	@GetMapping(path="/products/{id}")
	public Product getProductById(@PathVariable("id") Integer productId)
	{
		return productService.getProduct(productId);
	}
	
}
