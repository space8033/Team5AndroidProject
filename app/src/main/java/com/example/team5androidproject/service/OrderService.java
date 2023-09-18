package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.OrderHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderService {
    @GET("order/getDates")
    Call<List<String>> getDates(@Query("userId") String userId);
    @GET("order/history")
    Call<List<OrderHistory>> history(@Query("userId") String userId);

    static void loadImage(int product_No, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "order/getProductImage?product_no=" + product_No;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}