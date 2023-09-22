package com.example.team5androidproject.ui.viewHolder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Review;
import com.example.team5androidproject.service.ReviewService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.ReviewAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ReviewViewHolder";
    private int review_no;
    private TextView reviewName;
    private RatingBar ratingBar;
    private TextView reviewTitle;
    public RecyclerView reviewImageRecycler;
    private TextView reviewContent;
    private TextView registeredAt;
    private TextView productName;
    private ImageView deleteReview;
    private NavController navController;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        reviewName = (TextView) itemView.findViewById(R.id.txt_review_name);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar2);
        reviewTitle = (TextView) itemView.findViewById(R.id.review_title);
        reviewImageRecycler = (RecyclerView)  itemView.findViewById(R.id.review_image_recycler_view);
        reviewContent = (TextView) itemView.findViewById(R.id.review_content);
        registeredAt = (TextView) itemView.findViewById(R.id.txt_registered_at);
        productName = (TextView) itemView.findViewById(R.id.txt_inquiry_product);
        deleteReview = (ImageView) itemView.findViewById(R.id.review_delete);
    }

    public void setData(Review review, ReviewAdapter.OnItemClickListener onItemClickListener) {
        review_no = review.getReview_no();
        reviewName.setText(review.getReview_name());
        ratingBar.setRating(review.getReview_rating());
        reviewTitle.setText(review.getReview_title());
        reviewContent.setText(review.getReview_contents());
        registeredAt.setText(review.getReview_createdDate());
        productName.setText(review.getProduct_name());
        productName.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });
        ReviewService reviewService = ServiceProvider.getReviewService(itemView.getContext());

        deleteReview.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext())
                    .setTitle("삭제하기")
                    .setIcon(R.drawable.delete_24px)
                    .setMessage("정말로 삭제하시겠습니까?")
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Call<Void> call = reviewService.deleteReview(review_no);
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    navController.navigate(R.id.dest_mypage);
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                        }
                    })
                    .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create();
            alertDialog.show();
        });
    }
    public void setNavController(NavController navController) {
        this.navController = navController;
    }
}
