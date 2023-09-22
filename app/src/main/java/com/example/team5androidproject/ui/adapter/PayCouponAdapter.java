package com.example.team5androidproject.ui.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Coupon;
import com.example.team5androidproject.ui.viewHolder.PayCouponViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PayCouponAdapter extends RecyclerView.Adapter<PayCouponViewHolder> {

    private static final String TAG = "PayCouponAdapter";

    private List<Coupon> list = new ArrayList<>();
    private NavController navController;
    private Bundle bundle;


    @NonNull
    @Override
    public PayCouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.coupon_recycler, parent, false);
        PayCouponViewHolder payCouponHolder = new PayCouponViewHolder(itemView);

        return payCouponHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PayCouponViewHolder holder, int position) {
        Coupon coupon = list.get(position);
        holder.setData(coupon);
        holder.setBundle(bundle);
        holder.setNavController(navController);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Coupon> list) {
        this.list =list;
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }
}

