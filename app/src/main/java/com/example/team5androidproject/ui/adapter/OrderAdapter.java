package com.example.team5androidproject.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Order;
import com.example.team5androidproject.dto.Point;
import com.example.team5androidproject.service.MemberService;
import com.example.team5androidproject.service.OrderService;
import com.example.team5androidproject.ui.viewHolder.OrderViewHolder;
import com.example.team5androidproject.ui.viewHolder.PointViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder>{
    private static final String TAG = "OrderAdapter";
    private List<Order> list;

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_pay, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(itemView);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = list.get(position);
        holder.setData(order);
        Log.i(TAG, "Adapter에서 order: " + order.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Order> list) {
        this.list = list;
        Log.i(TAG, "Adapter에서 list: " + list.toString());
    }

    //쿠폰,적립금 사용전 총 가격 반환(orderFragment에서 사용)
    public int getTotalPayProductPrice() {
        int total = 0;
        for (Order order : list) {
            int price = order.getProduct_price() * order.getCart_qty();
            total += price;
        }
        return total;
    }
}

