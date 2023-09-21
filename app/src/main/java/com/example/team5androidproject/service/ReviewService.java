package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.dto.Review;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ReviewService {
    @GET("review/getReviewByUser")
    Call<List<Review>> getReviewByUser(@Query("userId") String userId);

    @GET("review/getReviewProductNo")
    Call<List<Review>> getReviewByProductNo(@Query("product_no") int product_no);

    @POST("review/writeReview")
    @Multipart
    Call<Void> writeReview(@Part MultipartBody.Part reviewTitle,
                           @Part MultipartBody.Part reviewContents,
                           @Part MultipartBody.Part reviewWriter,
                           @Part MultipartBody.Part productNo,
                           @Part MultipartBody.Part reviewRating,
                           @Part MultipartBody.Part file
    );

    @GET("review/deleteReview")
    Call<Void> deleteReview(@Query("review_no") int review_no);

    static void loadImage(int image_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "review/getReviewImage?image_no=" + image_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
