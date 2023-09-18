package com.example.team5androidproject.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Point;
import com.example.team5androidproject.ui.viewHolder.PointViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PointAdapter extends RecyclerView.Adapter<PointViewHolder> {
    private static final String TAG = "PointAdapter";
    private List<Point> list = new ArrayList<>();
    @NonNull
    @Override
    public PointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_point, parent, false);
        PointViewHolder pointViewHolder = new PointViewHolder(itemView);

        return pointViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PointViewHolder holder, int position) {
        Point point = list.get(position);
        Log.i(TAG, point.toString());
        holder.setData(point);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Point> list) {
        this.list = list;
    }
}
