package com.example.team5androidproject.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.dto.ProductDetail;
import com.example.team5androidproject.service.ProductService;

public class ProductDetailViewHolder extends RecyclerView.ViewHolder {
    private int product_no;
    private ImageView detail_img;

    public ProductDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        detail_img = (ImageView) itemView.findViewById(R.id.detail_img);
    }

    public void SetData(ProductDetail productDetail){
        product_no = productDetail.getProduct_no();
        ProductService.loadDetailImgDetail(productDetail.getProduct_no(),detail_img);
    }
}
