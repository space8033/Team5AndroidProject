package com.example.team5androidproject.ui.viewHolder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.OrderHistory;
import com.example.team5androidproject.dto.ProductDetail;
import com.example.team5androidproject.service.OrderService;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.HistoryDetailAdapter;
import com.example.team5androidproject.ui.fragment.MyPageFragment;
import com.example.team5androidproject.ui.fragment.OrderHistoryFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryDetailViewHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "HistoryDetailViewHolder";
    private TextView deliveryStatus;
    private TextView arriveAt;
    private ImageView productImage;
    private TextView productTitle;
    private TextView priceQuantity;
    public Button goDetail;
    public Button goReview;
    private NavController navController;

    public HistoryDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        deliveryStatus = (TextView) itemView.findViewById(R.id.txt_delivery_status);
        arriveAt = (TextView) itemView.findViewById(R.id.txt_arrive_at);
        productImage = (ImageView) itemView.findViewById(R.id.product_image);
        productTitle = (TextView) itemView.findViewById(R.id.txt_product_title);
        priceQuantity = (TextView) itemView.findViewById(R.id.txt_price_quantity);
        goDetail = (Button) itemView.findViewById(R.id.btn_go_detail);
        goReview = (Button) itemView.findViewById(R.id.btn_go_review);
    }

    public void setCard(OrderHistory orderHistory) {
        deliveryStatus.setText(orderHistory.getDeliveryStatus());
        arriveAt.setText(orderHistory.getArrival());
        OrderService.loadImage(orderHistory.getProductNo(), productImage);
        productTitle.setText(orderHistory.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        priceQuantity.setText(decimalFormat.format(orderHistory.getPrice()) + "원 * " + orderHistory.getQuantity() + "개");

        goDetail.setOnClickListener(v -> {
            getProductDetail(orderHistory.getProductNo());
        });
        Bundle bundle = new Bundle();
        bundle.putInt("pno", orderHistory.getProductNo());

        goReview.setOnClickListener(v -> {
            navController.navigate(R.id.dest_review_write, bundle);
        });
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    private void getProductDetail(int productNo) {
        ProductService productService = ServiceProvider.getProductService(navController.getContext());
        Call<ProductDetail> call = productService.getDetailList(productNo);
        call.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                ProductDetail productDetail = response.body();
                Bundle args = new Bundle();
                args.putSerializable("product", productDetail);
                if(productDetail.getImages_no() != null) {
                    args.putIntegerArrayList("imageNoList", new ArrayList<>(productDetail.getImages_no()));
                }

                navController.navigate(R.id.dest_detail, args);
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {

            }
        });
    }
}
