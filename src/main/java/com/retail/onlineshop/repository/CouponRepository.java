package com.retail.onlineshop.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.onlineshop.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {

	Optional<Coupon> findByCouponCode(String couponCode);

}
