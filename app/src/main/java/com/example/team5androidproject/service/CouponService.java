package com.example.team5androidproject.service;

import com.example.team5androidproject.dto.Coupon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CouponService {
    @GET("coupon/getCouponByUser")
    Call<List<Coupon>> getCouponByUser(@Query("usersId") String usersId);
    @GET("coupon/getCouponCount")
    Call<Integer> getCouponCount(@Query("usersId") String usersId);
    @GET("coupon.getPayCoupon")
    Call<Integer> getPayCoupon(@Query("userId") String userId);
}
