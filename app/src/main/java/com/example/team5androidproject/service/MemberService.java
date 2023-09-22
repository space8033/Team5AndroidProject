package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.dto.Inquiry;
import com.example.team5androidproject.dto.Login;
import com.example.team5androidproject.dto.MyPage;
import com.example.team5androidproject.dto.Point;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MemberService {
    //로그인 하기
    @GET("member/login")
    Call<Login> login(@Query("userId") String userId, @Query("password") String password);
    //마이페이지 정보 불러오기
    @GET("member/mypage")
    Call<MyPage> mypage(@Query("userId") String userId);
    //유저의 포인트 사용 및 적립 내역 불러오기
    @GET("member/point")
    Call<List<Point>> point(@Query("userId") String userId);
    //유저가 가진 총 포인트 불러오기
    @GET("member/totalPoint")
    Call<Integer> totalPoint(@Query("userId") String userId);
    //멤버가 한 문의 리스트 불러오기
    @GET("member/inquiry")
    Call<List<Inquiry>> inquiry(@Query("userId") String userId);
    //전체 문의 리스트 불러오기
    @GET("member/allInquiry")
    Call<List<Inquiry>> allInquiry();

    static void loadImage(String userId, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "member/myImage?userId=" + userId;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
