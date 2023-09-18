package com.example.team5androidproject.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.OrderHistory;
import com.example.team5androidproject.ui.viewHolder.HistoryDetailViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HistoryDetailAdapter extends RecyclerView.Adapter<HistoryDetailViewHolder> {
    List<OrderHistory> list = new ArrayList<>();
    private static final String TAG = "HistoryDetailAdapter";
    @NonNull
    @Override
    public HistoryDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.inner_order_history, parent, false);
        HistoryDetailViewHolder historyDetailViewHolder = new HistoryDetailViewHolder(itemView);

        return historyDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryDetailViewHolder holder, int position) {
        OrderHistory orderHistory = list.get(position);
        holder.setCard(orderHistory);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<OrderHistory> list)  {
        this.list = list;
    }
}
