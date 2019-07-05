package com.retail.onlineshop.controller;

import java.net.URI;
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

import com.retail.onlineshop.dto.CouponDTO;
import com.retail.onlineshop.dto.DefaultResponseDTO;
import com.retail.onlineshop.model.Coupon;
import com.retail.onlineshop.service.CouponService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CouponController {
	
	@Autowired
	CouponService couponService;
	
	@PostMapping(path="/coupon")
	public ResponseEntity<CouponDTO> saveCoupon(@RequestBody @Valid CouponDTO couponDTO)
	{
		Coupon savedCoupon = couponService.saveCoupon(couponDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedCoupon.getCouponId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path="/coupon/{id}")
	public ResponseEntity<CouponDTO> updateCoupon(@RequestBody @Valid CouponDTO couponDTO,@PathVariable("id") Integer couponId)
	{
		couponService.updateCoupon(couponDTO, couponId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path="/coupon/{id}")
	public void removeProduct(@PathVariable("id") Integer couponId)
	{
		couponService.deleteCoupon(couponId);
	}
	
	@GetMapping(path="/coupon")
	public List<CouponDTO> getAllCoupons()
	{
		List<CouponDTO> couponDTOList =  couponService.getAllCoupons();
		return couponDTOList;
	}
	
	@GetMapping(path="/coupon/{id}")
	public CouponDTO getProductById(@PathVariable("id") Integer couponId)
	{
		CouponDTO couponDTO = couponService.getCoupon(couponId);
		return couponDTO;
	}
	
	@GetMapping(path="/cart/{cartId}/coupon/apply/{couponCode}")
	public DefaultResponseDTO applyCoupon(@PathVariable("cartId") Integer cartId,@PathVariable("couponCode") String couponCode)
	{
		return couponService.applyCoupon(cartId, couponCode);
	}
	@GetMapping(path="/cart/{cartId}/coupon/unapply/{couponCode}")
	public DefaultResponseDTO removeCoupon(@PathVariable("cartId") Integer cartId,@PathVariable("couponCode") String couponCode)
	{
		 return couponService.unapplyCoupon(cartId, couponCode);
	}
}
