package com.example.team5androidproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.dto.ProductDetail;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductService {

    @GET("products/getProductList")
    Call<List<Product>> getProductList();

    @GET("products/getDetailList")
    Call<ProductDetail> getDetailList(@Query("product_no") int product_no);

    @GET("products/searchmobile") // 서버에서 검색을 처리할 엔드포인트 경로
    Call<List<Product>> searchProducts(@Query("keyword") String keyword);

    static void loadImage(int product_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "products/fileDownload?product_no=" + product_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    static void loadDetail(int product_no, ImageView imageView){
        String url = NetworkInfo.BASE_URL+"products/getDetailImgDetail?product_no="+ product_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
