package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.dto.Login;
import com.example.team5androidproject.dto.MyPage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MemberService {
    @GET("member/login")
    Call<Login> login(@Query("userId") String userId, @Query("password") String password);

    @GET("member/mypage")
    Call<MyPage> mypage(@Query("userId") String userId);

    static void loadImage(String userId, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "member/myImage?userId=" + userId;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
