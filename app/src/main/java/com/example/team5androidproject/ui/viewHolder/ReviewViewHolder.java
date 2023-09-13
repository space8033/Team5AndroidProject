package com.example.team5androidproject.ui.viewHolder;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Review;
import com.example.team5androidproject.service.ReviewService;
import com.example.team5androidproject.service.ServiceProvider;

import java.util.List;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private int review_no;
    private RatingBar ratingBar;
    private TextView reviewTitle;
    private RecyclerView reviewImageRecycler;
    private TextView reviewContent;
    private TextView registeredAt;
    private TextView productName;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar2);
        reviewTitle = (TextView) itemView.findViewById(R.id.review_title);
        reviewImageRecycler = (RecyclerView)  itemView.findViewById(R.id.review_image_recycler_view);
        reviewContent = (TextView) itemView.findViewById(R.id.review_content);
        registeredAt = (TextView) itemView.findViewById(R.id.txt_registered_at);
        productName = (TextView) itemView.findViewById(R.id.txt_product_name);
    }

    public void setData(Review review) {
        review_no = review.getReview_no();
        ratingBar.setRating(review.getReview_rating());
        reviewTitle.setText(review.getReview_title());
        reviewContent.setText(review.getReview_contents());
        registeredAt.setText(review.getReview_createdDate());
        productName.setText(review.getProduct_name());
    }
}
