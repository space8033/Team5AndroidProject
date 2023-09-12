package com.example.team5androidproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Review;
import com.example.team5androidproject.ui.viewHolder.ReviewViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    List<Review> list= new ArrayList<>();


    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.review_recycle, parent, false);
        ReviewViewHolder reviewViewHolder =new ReviewViewHolder(itemView);

        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review =list.get(position);
        holder.setData(review);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Review> list) {
        this.list = list;
    }
}
