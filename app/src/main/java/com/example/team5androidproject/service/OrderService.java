package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.Order;
import com.example.team5androidproject.dto.OrderHistory;
import com.example.team5androidproject.dto.OrderUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {
    @GET("order/getDates")
    Call<List<String>> getDates(@Query("userId") String userId);
    @GET("order/history")
    Call<List<OrderHistory>> history(@Query("userId") String userId);
    @GET("order/getOrderInfo") //구매자 정보를 가져온다
    Call<OrderUser> getOrderItems(@Query("cart_no") int cart_no);
   /* @GET("order/getOrderItems") //구매할 상품을 가져온다
    Call<List<Order>> getOrderInfo(@Query("cart_no") int cart_no);*/
    @POST("order/getOrderInfos") //리스트로 받을때 post로 받아야 한다
    Call<List<Order>> getOrderInfos(@Body List<Integer> cartNos); //여기도 @Query 대신에 @Body로


    static void loadImage(int product_No, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "order/getProductImage?product_no=" + product_No;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
