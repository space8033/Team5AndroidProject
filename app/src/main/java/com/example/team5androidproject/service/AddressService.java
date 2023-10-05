package com.example.team5androidproject.service;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddressService {

    @POST("address/addressRegister")
    Call<Void> addressRegister(@Body String address_receiver,
                               @Body String address_roadAddress,
                               @Body String address_jibunAddress,
                               @Body String address_extraAddress,
                               @Body String address_detail,
                               @Body String users_users_id,
                               @Body String users_phone
                               );
}
