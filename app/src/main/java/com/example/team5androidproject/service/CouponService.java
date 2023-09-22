package com.example.team5androidproject.service;

import com.example.team5androidproject.dto.Coupon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CouponService {
    //유저아이디로 쿠폰 가져오기
    @GET("coupon/getCouponByUser")
    Call<List<Coupon>> getCouponByUser(@Query("usersId") String usersId);
    //유저가 가진 쿠폰 총 수 가져오기
    @GET("coupon/getCouponCount")
    Call<Integer> getCouponCount(@Query("usersId") String usersId);
    @GET("coupon.getPayCoupon")
    Call<Integer> getPayCoupon(@Query("userId") String userId);
}
