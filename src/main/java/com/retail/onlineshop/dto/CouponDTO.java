package com.retail.onlineshop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.retail.onlineshop.model.Coupon;

public class CouponDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer couponId;
	@NotNull
	private String couponCode;
	@NotNull
	private String couponDescription;
	@PositiveOrZero
	private BigDecimal couponValidPrice;
	@FutureOrPresent
	private Date couponValidDate;
	@NotNull
	@Positive
	@Min(value=1,message="Minimum allowed coupon discount is 1")
	@Max(value=15,message="Maximum allowed coupon discount is 15")
	private BigDecimal couponDiscount;
	
	protected CouponDTO() {
		
	}
	
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
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
	public Date getCouponValidDate() {
		return couponValidDate;
	}
	public void setCouponValidDate(Date couponValidDate) {
		this.couponValidDate = couponValidDate;
	}
	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static CouponDTO convertoDTO(Coupon coupon)
	{
		CouponDTO couponDTO = new CouponDTO();
		
		couponDTO.setCouponId(coupon.getCouponId());
		couponDTO.setCouponCode(coupon.getCouponCode());
		couponDTO.setCouponDescription(coupon.getCouponDescription());
		couponDTO.setCouponValidPrice(coupon.getCouponValidPrice());
		couponDTO.setCouponValidDate(coupon.getCouponValidDate());
		couponDTO.setCouponDiscount(coupon.getCouponDiscount());
		
		return couponDTO;
	}
}
