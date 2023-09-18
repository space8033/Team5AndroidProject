package com.example.team5androidproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.service.ProductService;

import java.util.List;

public class DetailThumbnailAdapter extends RecyclerView.Adapter<DetailThumbnailAdapter.ViewHolder> {
    private List<Integer> imageNoList;

    public DetailThumbnailAdapter(List<Integer> imageNoList) {
        this.imageNoList = imageNoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_detail_thumbnail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int imageNo = imageNoList.get(position);
        ProductService.loadDetailThumbnail(imageNo, holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageNoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnail_view);
        }
    }
}
