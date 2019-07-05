package com.retail.onlineshop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.onlineshop.dto.CouponDTO;
import com.retail.onlineshop.dto.DefaultResponseDTO;
import com.retail.onlineshop.exception.CouponNotFoundException;
import com.retail.onlineshop.model.Cart;
import com.retail.onlineshop.model.Coupon;
import com.retail.onlineshop.repository.CouponRepository;

@Service
public class CouponService {
	
	@Autowired
	CouponRepository couponRepository;
	
	@Autowired
	CartService cartService;
	
	public Coupon saveCoupon(CouponDTO couponDTO)
	{
		Coupon coupon = Coupon.converToEntity(couponDTO);
		Coupon savedCoupon = couponRepository.save(coupon);
		return savedCoupon;
	}
	
	public Coupon updateCoupon(CouponDTO couponDTO,Integer couponId)
	{
		Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
		if(optionalCoupon.isPresent())
		{
			Coupon coupon = Coupon.converToEntity(couponDTO);
			Coupon savedCoupon = couponRepository.save(coupon);
			return savedCoupon;
		}
		else
			throw new CouponNotFoundException("Coupon Id "+ couponId+" Not Found");
	}

	public void deleteCoupon(Integer couponId)
	{
		Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
		if(optionalCoupon.isPresent())
		{
			couponRepository.delete(optionalCoupon.get());
		}
		else
			throw new CouponNotFoundException("Coupon Id "+ couponId+" Not Found");
	}
	
	public List<CouponDTO> getAllCoupons()
	{
		List<CouponDTO> couponDTOList = new ArrayList<CouponDTO>();
		List<Coupon> couponList = couponRepository.findAll();
		Iterator<Coupon> iterator = couponList.iterator();
		while(iterator.hasNext())
		{
			Coupon coupon = iterator.next();
			couponDTOList.add(CouponDTO.convertoDTO(coupon));
		}
		return couponDTOList;
	}
	
	public CouponDTO getCoupon(Integer couponId)
	{
		Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
		if(optionalCoupon.isPresent())
		{
			Coupon coupon = optionalCoupon.get();
			return CouponDTO.convertoDTO(coupon);
		}
		else
			throw new CouponNotFoundException("Coupon Id "+ couponId+" Not Found");
	}
	
	public DefaultResponseDTO applyCoupon(Integer cartId,String couponCode) {
		Coupon coupon;
		BigDecimal totalCouponDiscount = new BigDecimal(00.00);
		Cart cart = cartService.viewCart(cartId);
		Optional<Coupon> optionalCoupon = couponRepository.findByCouponCode(couponCode);
		if(optionalCoupon.isPresent())
			coupon = optionalCoupon.get();
		else
			coupon=null;
		
		Boolean isValidCartPrice = cart.getCartPrice().compareTo(coupon.getCouponValidPrice())==-1?false:true;
		Boolean isValidCartDate = coupon.getCouponValidDate().before(new Date())?false:true;
		Boolean alreadyApplied = cart.getCouponSet().contains(coupon);
		
		if(isValidCartPrice && isValidCartDate && !alreadyApplied && coupon!=null )
		{
			cart.applyCoupon(coupon);
			Set<Coupon> couponSet = cart.getCouponSet();
			Iterator<Coupon> iterator = couponSet.iterator();
			while(iterator.hasNext())
			{
				Coupon appliedCoupon = iterator.next();
				totalCouponDiscount=totalCouponDiscount.add(appliedCoupon.getCouponDiscount());
			}
			cart.setCartDiscount(totalCouponDiscount);
			cartService.saveCart(cart);
			DefaultResponseDTO responseDTO = new DefaultResponseDTO("Coupon applied successfully!");
			return responseDTO;
		}
		else
		{
			DefaultResponseDTO responseDTO;
			if(alreadyApplied)
				responseDTO = new DefaultResponseDTO("Coupon already applied!");
			else if(!isValidCartPrice)
				responseDTO = new DefaultResponseDTO("Minimum amount for coupon to be applied is "+coupon.getCouponValidPrice());
			else if(!isValidCartDate)
				responseDTO = new DefaultResponseDTO("Coupon Expired!");
			else
				responseDTO = new DefaultResponseDTO("Invalid Coupon!");
			return responseDTO;
		}
	}
	public DefaultResponseDTO unapplyCoupon(Integer cartId,String couponCode) {
		Coupon coupon;
		BigDecimal totalCouponDiscount = new BigDecimal(00.00);
		Cart cart = cartService.viewCart(cartId);
		Optional<Coupon> optionalCoupon = couponRepository.findByCouponCode(couponCode);
		if(optionalCoupon.isPresent())
			coupon = optionalCoupon.get();
		else
			coupon=null;
		Boolean appliedCoupon = cart.getCouponSet().contains(coupon);
		if(coupon!=null && appliedCoupon)
		{
			coupon = optionalCoupon.get();
		
			cart.removeCoupon(coupon);
			Set<Coupon> couponSet = cart.getCouponSet();
			Iterator<Coupon> iterator = couponSet.iterator();
			while(iterator.hasNext())
			{
				Coupon presentCoupon = iterator.next();
				totalCouponDiscount=totalCouponDiscount.add(presentCoupon.getCouponDiscount());
			}
			cart.setCartDiscount(totalCouponDiscount);
			cartService.saveCart(cart);
			DefaultResponseDTO responseDTO = new DefaultResponseDTO("Coupon removed successfully!");
			return responseDTO;
		}
		else
		{
			DefaultResponseDTO responseDTO;
			if(!appliedCoupon)
				responseDTO = new DefaultResponseDTO("Coupon has not been applied to cart");
			else
				responseDTO = new DefaultResponseDTO("Invalid Coupon!");
			return responseDTO;
		}
	}
	
}
