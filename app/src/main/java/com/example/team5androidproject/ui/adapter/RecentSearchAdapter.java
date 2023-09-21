package com.example.team5androidproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;

import java.util.List;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder> {
    private List<String> recentSearchList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String searchKeyword);
        void onDeleteClick(int position);
    }
    public RecentSearchAdapter(List<String> recentSearchList, OnItemClickListener listener) {
        this.recentSearchList = recentSearchList;
        this.onItemClickListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecentSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_search, parent, false);
        return new RecentSearchViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentSearchViewHolder holder, int position) {
        String searchKeyword = recentSearchList.get(position);
        holder.bind(searchKeyword);

        holder.recentSearchButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(searchKeyword);
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recentSearchList.size();
    }

    public class RecentSearchViewHolder extends RecyclerView.ViewHolder {
        private Button recentSearchButton;
        private Button deleteButton;

        public RecentSearchViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            recentSearchButton = itemView.findViewById(R.id.recent_search_button);
            deleteButton = itemView.findViewById(R.id.delete_button);

            deleteButton.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });
        }

        public void bind(String searchKeyword) {
            recentSearchButton.setText(searchKeyword);
        }
    }
}