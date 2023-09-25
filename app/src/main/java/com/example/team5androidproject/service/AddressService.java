package com.example.team5androidproject.service;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddressService {

    @POST("address/addressRegister")
    Call<Void> addressRegister(@Part MultipartBody.Part address_receiver,
                               @Part MultipartBody.Part address_roadAddress,
                               @Part MultipartBody.Part address_jibunAddress,
                               @Part MultipartBody.Part address_extraAddress,
                               @Part MultipartBody.Part address_detail,
                               @Part MultipartBody.Part users_users_id,
                               @Part MultipartBody.Part users_phone
                               );
}
