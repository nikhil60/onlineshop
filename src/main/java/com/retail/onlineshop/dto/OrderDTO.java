package com.retail.onlineshop.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.retail.onlineshop.model.Order;

public class OrderDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	@NotNull
	private Integer productId;
	@NotNull
	private String productName;
	@NotNull
	@Positive
	private BigDecimal productPrice;
	@NotNull
	@Positive
	private Integer orderQty;
	@NotNull
	@Positive
	private BigDecimal orderPrice;
	@NotNull
	private Integer cartId;
	
	public OrderDTO()
	{
		super();
	}
	
	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public static OrderDTO convertToDTO(Order order)
	{
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setProductId(order.getProduct().getProductId());
		orderDTO.setProductName(order.getProduct().getProductName());
		orderDTO.setProductPrice(order.getProduct().getProductPrice());
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setOrderQty(order.getOrderQuantity());
		orderDTO.setOrderPrice(order.getOrderPrice());
		orderDTO.setCartId(order.getCart().getCartId());
		return orderDTO;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", productId=" + productId + ", productName=" + productName
				+ ", productPrice=" + productPrice + ", orderQty=" + orderQty + ", orderPrice=" + orderPrice
				+ ", cartId=" + cartId + "]";
	}
	
}
