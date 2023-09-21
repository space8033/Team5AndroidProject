package com.example.team5androidproject.ui.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.ui.adapter.RecentSearchAdapter;

public class RecentSearchViewHolder extends RecyclerView.ViewHolder{
    private Button recentSearchButton;
    private Button deleteButton;

    public RecentSearchViewHolder(@NonNull View itemView, RecentSearchAdapter.OnItemClickListener listener) {
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

    public void setDeleteButtonClickListener(View.OnClickListener listener) {
        deleteButton.setOnClickListener(listener);
    }
}
