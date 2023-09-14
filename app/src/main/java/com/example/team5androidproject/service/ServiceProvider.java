package com.example.team5androidproject.service;

import android.content.Context;

import com.example.team5androidproject.datastore.AppKeyValueStore;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {
    private static final String TAG = "ServiceProvider";

    public static Retrofit getRetrofit(Context context){
        OkHttpClient.Builder okHttpClientBuilder =new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder httBuilder =request.url().newBuilder();
                String userId = AppKeyValueStore.getValue(context, "userId");
                String password = AppKeyValueStore.getValue(context, "password");

                if(userId != null && password != null) {
                    httBuilder.addQueryParameter("loginId", userId);
                    httBuilder.addQueryParameter("userPassword", password);
                }else {

                }

                HttpUrl httpUrl = httBuilder.build();

                Request updateRequest = request.newBuilder()
                        .url(httpUrl)
                        .build();

                return chain.proceed(updateRequest);
            }
        });

        OkHttpClient okHttpClient = okHttpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkInfo.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
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

    public static CouponService getCouponService(Context context) {
        CouponService couponService = getRetrofit(context).create(CouponService.class);
        return couponService;
    }

    public static MemberService getMemberService(Context context) {
        MemberService memberService = getRetrofit(context).create(MemberService.class);
        return memberService;
    }

}
