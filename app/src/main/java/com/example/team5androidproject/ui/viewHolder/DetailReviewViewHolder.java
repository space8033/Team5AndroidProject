package com.example.team5androidproject.ui.viewHolder;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Review;

public class DetailReviewViewHolder extends RecyclerView.ViewHolder {
    private int review_no;
    private int product_no;
    private TextView reviewName;
    private RatingBar ratingBar;
    private TextView reviewTitle;
    public RecyclerView reviewImageRecycler;
    private TextView reviewContent;
    private TextView registeredAt;


    public DetailReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        reviewName = (TextView) itemView.findViewById(R.id.txt_review_name);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar2);
        reviewTitle = (TextView) itemView.findViewById(R.id.review_title);
        reviewImageRecycler = (RecyclerView)  itemView.findViewById(R.id.review_image_recycler_view);
        reviewContent = (TextView) itemView.findViewById(R.id.review_content);
        registeredAt = (TextView) itemView.findViewById(R.id.txt_registered_at);
    }

    public void setData(Review review) {
        review_no = review.getReview_no();
        product_no = review.getProduct_no();
        reviewName.setText(review.getReview_name());
        ratingBar.setRating(review.getReview_rating());
        reviewTitle.setText(review.getReview_title());
        reviewContent.setText(review.getReview_contents());
        registeredAt.setText(review.getReview_createdDate());
    }
}
