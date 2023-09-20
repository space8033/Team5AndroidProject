package com.example.team5androidproject.ui.viewHolder;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.ui.adapter.HistoryDetailAdapter;

public class OrderHistoryViewHolder extends RecyclerView.ViewHolder{
    private TextView orderedAt;
    public RecyclerView recyclerView;
    private NavController navController;
    private static final String TAG = "OrderHistoryViewHolder";
    public OrderHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        orderedAt = (TextView) itemView.findViewById(R.id.txt_order_at);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.inner_recycler_view);
    }

    public void setData(String order) {
        orderedAt.setText(order);
    }
    public void setNavController(NavController navController) {
        this.navController = navController;
    }
}
