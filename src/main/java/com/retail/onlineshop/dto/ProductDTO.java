package com.retail.onlineshop.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.retail.onlineshop.model.Product;

public class ProductDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer productId;
	@NotNull
	private String productName;
	@Positive
	@NotNull
	private BigDecimal productPrice;
	
	public ProductDTO()
	{
		super();
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	public static ProductDTO convertToDTO(Product product)
	{
		System.out.print(product.getProductPrice());
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductPrice(product.getProductPrice());
		return productDTO;
	}
}
