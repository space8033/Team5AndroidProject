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

    public static ProductService getProductService(Context context) {
        ProductService productService = getRetrofit(context).create(ProductService.class);
        return productService;
    }

    public static CartService getCartService(Context context){
        CartService cartService = getRetrofit(context).create(CartService.class);
        return cartService;
    }

    public static ReviewService getReviewService(Context context) {
        ReviewService reviewService = getRetrofit(context).create(ReviewService.class);
        return reviewService;
    }

}
