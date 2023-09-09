package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.dto.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListService {

    @GET("products/getProductList")
    Call<List<Product>> getProductList();

    static void loadImage(int product_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "products/fileDownload?product_no=" + product_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
