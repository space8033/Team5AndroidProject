package com.example.team5androidproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Review;
import com.example.team5androidproject.ui.viewHolder.DetailReviewViewHolder;
import com.example.team5androidproject.ui.viewHolder.ReviewViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailReviewAdapter extends RecyclerView.Adapter<DetailReviewViewHolder>{
    private List<Review> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private static final String TAG = "DetailReviewAdapter";
    @NonNull
    @Override
    public DetailReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_detail_review_recycle, parent, false);
        DetailReviewViewHolder detailreviewViewHolder = new DetailReviewViewHolder(itemView);


        return detailreviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailReviewViewHolder holder, int position) {
        Review review = list.get(position);
        holder.setData(review);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false
        );

        List<Integer> imageList = review.getImages_no();

        ReviewImageAdapter reviewImageAdapter = new ReviewImageAdapter(imageList);
        holder.reviewImageRecycler.setLayoutManager(linearLayoutManager);
        holder.reviewImageRecycler.setAdapter(reviewImageAdapter);
    }

    @Override
    public int getItemCount() {
        if(list == null) {
            return 0;
        }
        return list.size();
    }
    public void setList(List<Review> list) {
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
