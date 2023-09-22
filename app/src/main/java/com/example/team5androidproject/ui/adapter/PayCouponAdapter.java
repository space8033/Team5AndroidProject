package com.example.team5androidproject.ui.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Coupon;
import com.example.team5androidproject.dto.PayCoupon;
import com.example.team5androidproject.ui.viewHolder.CouponViewHolder;
import com.example.team5androidproject.ui.viewHolder.PayCouponViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PayCouponAdapter extends RecyclerView.Adapter<PayCouponViewHolder> {
    private static final String TAG = "PayCouponAdapter";
    private List<PayCoupon> list = new ArrayList<>();



    @NonNull
    @Override
    public PayCouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.paycoupon_recycler, parent, false);
        PayCouponViewHolder payCouponViewHolder = new PayCouponViewHolder(itemView);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PayCouponViewHolder holder, int position) {
        PayCoupon payCoupon = list.get(position);
        holder.setData(payCoupon);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<PayCoupon> list) {
        this.list =list;
    }
}
