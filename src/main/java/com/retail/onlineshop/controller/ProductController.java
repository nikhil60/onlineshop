package com.retail.onlineshop.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.retail.onlineshop.dto.ProductDTO;
import com.retail.onlineshop.model.Product;
import com.retail.onlineshop.service.ProductService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping(path="/products")
	public ResponseEntity<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO)
	{
		Product product = Product.convertToEntity(productDTO);
		
		Product savedProduct = productService.saveProduct(product);
		
		ProductDTO savedProductDTO = ProductDTO.convertToDTO(savedProduct);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedProductDTO.getProductId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path="/products/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDTO,@PathVariable("id") Integer productId)
	{
		Product product = Product.convertToEntity(productDTO);
		
		productService.updateProduct(product,productId);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path="/products/{id}")
	public void removeProduct(@PathVariable("id") Integer productId)
	{
		productService.removeProduct(productId);
	}
	
	@GetMapping(path="/products")
	public List<ProductDTO> getAllProducts()
	{
		List<Product> productList =  productService.getAllProducts();
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		Iterator<Product> it = productList.iterator();
		while(it.hasNext())
		{
			productDTOList.add(ProductDTO.convertToDTO((Product) it.next()));
		}
		return productDTOList;
	}
	
	@GetMapping(path="/products/{id}")
	public ProductDTO getProductById(@PathVariable("id") Integer productId)
	{
		Product product = productService.getProduct(productId);
		return ProductDTO.convertToDTO(product);
	}
	
}
