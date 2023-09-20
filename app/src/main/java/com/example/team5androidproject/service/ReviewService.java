package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.dto.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReviewService {
    @GET("review/getReviewByUser")
    Call<List<Review>> getReviewByUser(@Query("userId") String userId);

    @GET("review/getReviewProductNo")
    Call<List<Review>> getReviewByProductNo(@Query("product_no") int product_no);

    static void loadImage(int image_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "review/getReviewImage?image_no=" + image_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
