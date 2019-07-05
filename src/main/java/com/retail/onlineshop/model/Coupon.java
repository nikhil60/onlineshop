package com.retail.onlineshop.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.retail.onlineshop.dto.CouponDTO;

@Entity
public class Coupon {
	
	@Id
	@GeneratedValue
	private Integer couponId;
	private String couponCode;
	private String couponDescription;
	private BigDecimal couponValidPrice;
	private Date couponValidDate;
	private BigDecimal couponDiscount;
	@ManyToOne
	@JoinTable(name="cart_coupons",
    joinColumns={@JoinColumn(name="coupon_id", referencedColumnName="couponId")},
    inverseJoinColumns={@JoinColumn(name="cart_id", referencedColumnName="cartId")})
	private Cart cart;
	
	protected Coupon()
	{}
	
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponDescription() {
		return couponDescription;
	}
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
	public BigDecimal getCouponValidPrice() {
		return couponValidPrice;
	}
	public void setCouponValidPrice(BigDecimal couponValidPrice) {
		this.couponValidPrice = couponValidPrice;
	}
	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Date getCouponValidDate() {
		return couponValidDate;
	}

	public void setCouponValidDate(Date couponValidDate) {
		this.couponValidDate = couponValidDate;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public static Coupon converToEntity(CouponDTO couponDTO)
	{
		Coupon coupon = new Coupon();
		
		coupon.setCouponId(couponDTO.getCouponId());
		coupon.setCouponCode(couponDTO.getCouponCode());
		coupon.setCouponDescription(couponDTO.getCouponDescription());
		coupon.setCouponValidPrice(couponDTO.getCouponValidPrice());
		coupon.setCouponValidDate(couponDTO.getCouponValidDate());
		coupon.setCouponDiscount(couponDTO.getCouponDiscount());
		
		return coupon;
	}
	
}
