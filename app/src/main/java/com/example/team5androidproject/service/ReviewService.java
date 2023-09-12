package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.dto.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReviewService {
    @GET
    Call<List<Review>> getReviewByUser();

    static void loadImage(int review_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "products/fileDownload?product_no=" + review_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
