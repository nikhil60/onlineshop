package com.retail.onlineshop.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cart {
	@Id
	@GeneratedValue
	private Integer cartId;
	
	private Date creationDate;
	
	private Date lastUpdationDate;
	
	@OneToOne(mappedBy="cart")
	private Customer customer;
	
	@OneToMany(mappedBy="cart",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Order> orderSet = new HashSet<Order>();
	
	private BigDecimal cartPrice;
	
	private BigDecimal cartDiscount;
	
	private BigDecimal cartTax;
	
	private BigDecimal cartTotal;
	
	@OneToMany(mappedBy="cart",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Coupon> couponSet = new HashSet<Coupon>();

	public Cart()
	{}
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdationDate() {
		return lastUpdationDate;
	}

	public void setLastUpdationDate(Date lastUpdationDate) {
		this.lastUpdationDate = lastUpdationDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Order> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}
	
	public void addOrder(Order order)
	{
		orderSet.add(order);
		order.setCart(this);
	}
	
	public void removeOrder(Order order)
	{
		orderSet.remove(order);
		order.setCart(null);
	}

	public BigDecimal getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(BigDecimal cartPrice) {
		this.cartPrice = cartPrice;
	}
	
	public void applyCoupon(Coupon coupon)
	{
		couponSet.add(coupon);
		coupon.setCart(this);
	}

	public void removeCoupon(Coupon coupon)
	{
		couponSet.remove(coupon);
		coupon.setCart(null);
	}
	
	
	public Set<Coupon> getCouponSet() {
		return couponSet;
	}

	public void setCouponSet(Set<Coupon> couponSet) {
		this.couponSet = couponSet;
	}

	public BigDecimal getCartDiscount() {
		return cartDiscount;
	}

	public void setCartDiscount(BigDecimal cartDiscount) {
		this.cartDiscount = cartDiscount;
	}

	public BigDecimal getCartTax() {
		return cartTax;
	}

	public void setCartTax(BigDecimal cartTax) {
		this.cartTax = cartTax;
	}

	public BigDecimal getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(BigDecimal cartTotal) {
		this.cartTotal = cartTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		return true;
	}
	
}
