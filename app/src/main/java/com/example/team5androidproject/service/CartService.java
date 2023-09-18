package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.dto.Cart;
import com.example.team5androidproject.dto.ProductDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface CartService {

    @GET("cart/getCartList")
    Call<List<Cart>> getCartList(
            @Query("user_Id") String user_Id
    );
    @GET("cart/getCartCount")
    Call<Integer> getCartCount(
            @Query("user_Id") String user_Id
    );
    @GET("cart/updateCartCount")
    Call<Void> updateCart(
            @Query("cart_no") int cart_no, // 업데이트할 카트 항목의 번호
            @Query("cart_qty") int cart_qty // 새로운 수량
    );
    @GET("cart/deleteOneCart")
    Call<Void> deleteOneCart(
            @Query("cart_no") int cart_no
    );

    static void loadImage(int product_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "cart/fileDownload?product_no=" + product_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}
