package com.retail.onlineshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.onlineshop.exception.ProductNotFoundException;
import com.retail.onlineshop.model.Product;
import com.retail.onlineshop.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public Product saveProduct(Product product)
	{
		Product savedProduct  = productRepository.save(product);
		return savedProduct;
	}
	
	public void updateProduct(Product product,Integer productId)
	{
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent())
			productRepository.save(product);
		else
			throw new ProductNotFoundException("Product Id "+product.getProductId()+" Not Found");
	}
	
	public void removeProduct(Integer productId)
	{
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent())
			productRepository.delete(optionalProduct.get());
		else
			throw new ProductNotFoundException("Product Id "+productId+" Not Found");
	}
	
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}
	
	public Product getProduct(Integer productId)
	{
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent())
			return optionalProduct.get();
		else
			throw new ProductNotFoundException("Product Id "+productId+" Not Found");
	}
}
