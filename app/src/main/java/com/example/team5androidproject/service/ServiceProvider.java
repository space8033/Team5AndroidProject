package com.example.team5androidproject.service;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {
    private static final String TAG = "ServiceProvider";

    public static Retrofit getRetrofit(Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkInfo.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    //위에서 만들어진 retrofit으로 interface생성

    public static ListService getListService(Context context) {
        ListService listService = getRetrofit(context).create(ListService.class);
        return listService;
    }
}
