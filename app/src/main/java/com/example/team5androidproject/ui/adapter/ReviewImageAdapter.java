package com.example.team5androidproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.ui.viewHolder.ReviewImageViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReviewImageAdapter extends RecyclerView.Adapter<ReviewImageViewHolder> {
    List<Integer> list = new ArrayList<>();
    @NonNull
    @Override
    public ReviewImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View itemView =layoutInflater.inflate(R.layout.review_recycle, parent, false);
        ReviewImageViewHolder reviewImageViewHolder = new ReviewImageViewHolder(itemView);
        return reviewImageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewImageViewHolder holder, int position) {
        Integer i = list.get(position);
        holder.setImage(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
