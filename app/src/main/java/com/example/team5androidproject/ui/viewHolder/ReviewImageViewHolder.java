package com.example.team5androidproject.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.service.ReviewService;

public class ReviewImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    public ReviewImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.recycler_review_img);
    }

    public void setImage(int imageNo) {
        ReviewService.loadImage(imageNo, imageView);
    }
}
