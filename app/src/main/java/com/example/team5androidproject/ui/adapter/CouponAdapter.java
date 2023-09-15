package com.example.team5androidproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Coupon;
import com.example.team5androidproject.ui.viewHolder.CouponViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponViewHolder> {
    private static final String TAG = "CouponAdapter";

    private List<Coupon> list = new ArrayList<>();
    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.coupon_recycler, parent, false);
        CouponViewHolder couponViewHolder = new CouponViewHolder(itemView);

        return couponViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        Coupon coupon = list.get(position);
        holder.setData(coupon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Coupon> list) {
        this.list =list;
    }
}
