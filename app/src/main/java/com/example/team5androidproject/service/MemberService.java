package com.example.team5androidproject.service;

import com.example.team5androidproject.dto.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MemberService {
    @GET("member/login")
    Call<Login> login(@Query("userId") String userId, @Query("password") String password);

}
