package com.example.team5androidproject.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.ui.viewHolder.ReviewImageViewHolder;

import java.util.List;

public class ReviewImageAdapter extends RecyclerView.Adapter<ReviewImageViewHolder> {
    private static final String TAG = "ReviewImageAdapter";
    private List<Integer> list;

    public ReviewImageAdapter(List<Integer> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ReviewImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.review_image, parent, false);
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
}
