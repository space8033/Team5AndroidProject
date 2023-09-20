package com.example.team5androidproject.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.OrderHistory;
import com.example.team5androidproject.service.OrderService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.viewHolder.OrderHistoryViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryViewHolder> {
    private List<String> dates = new ArrayList<>();
    private List<OrderHistory> histories = new ArrayList<>();
    private NavController navController;
    private static final String TAG = "OrderHistoryAdapter";
    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.outer_order_history, parent, false);
        OrderHistoryViewHolder orderHistoryViewHolder = new OrderHistoryViewHolder(itemView);
        orderHistoryViewHolder.setNavController(navController);

        return orderHistoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        String s = dates.get(position);
        holder.setData(s);

        List<OrderHistory> filtered = new ArrayList<>();
        for(OrderHistory oh : histories) {
            if(oh.getOrderDate().equals(s)) {
                filtered.add(oh);
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false
        );
        HistoryDetailAdapter historyDetailAdapter = new HistoryDetailAdapter();
        historyDetailAdapter.setList(filtered);
        historyDetailAdapter.setNavController(navController);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setAdapter(historyDetailAdapter);

    }
    @Override
    public int getItemCount() {
        return dates.size();
    }

    public void setHistoryList(List<OrderHistory> list) {
        this.histories = list;
    }
    public void setList(List<String> list) {
        this.dates = list;
    }
    public void setNavController(NavController navController) {
        this.navController = navController;
    }
}
