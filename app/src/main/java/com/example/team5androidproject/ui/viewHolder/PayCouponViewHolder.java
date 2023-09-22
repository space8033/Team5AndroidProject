package com.example.team5androidproject.ui.viewHolder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Coupon;

public class PayCouponViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "PayCouponViewHolder";
    private TextView createdDate;
    private TextView expiredDate;
    private ImageView couponImage;
    private NavController navController;
    private Bundle bundle;
    public PayCouponViewHolder(@NonNull View itemView) {
        super(itemView);
        createdDate = (TextView) itemView.findViewById(R.id.txt_coupon_created_at);
        expiredDate = (TextView) itemView.findViewById(R.id.txt_coupon_deleted_at);
        couponImage = (ImageView) itemView.findViewById(R.id.coupon_image);
    }

    public void setData(Coupon coupon) {
        createdDate.setText(coupon.getCoupon_createdDate());
        expiredDate.setText(coupon.getCoupon_expiredDate());
        if(String.valueOf(coupon.getCoupon_type()).equals("BIRTHDAY_COUPON")) {
            couponImage.setImageResource(R.drawable.birthday_coupon);
        }else if(String.valueOf(coupon.getCoupon_type()).equals("DEL_FREE_COUPON")) {
            couponImage.setImageResource(R.drawable.delivery_coupon);
        }else if(String.valueOf(coupon.getCoupon_type()).equals("WELCOME_COUPON")) {
            couponImage.setImageResource(R.drawable.welcome_coupon);
        }
            couponImage.setOnClickListener(v->{
                Log.i(TAG, "쿠폰의 값" + coupon.toString());
                bundle.putSerializable("coupon",coupon);
                Log.i(TAG, "setData: " + bundle);
                navController.navigate(R.id.dest_order, bundle);
            });
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }
}
