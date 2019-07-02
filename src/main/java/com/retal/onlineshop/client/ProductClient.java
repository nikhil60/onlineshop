package com.retal.onlineshop.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.retail.onlineshop.model.Product;

public class ProductClient {
	
	@Test
	public void getProducts() throws URISyntaxException
	{
		final String baseurl = "http://localhost:8080/products";
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(baseurl);
		
		ResponseEntity<Product> result = restTemplate.getForEntity(uri, Product.class);
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody()!=null);
	}
}
