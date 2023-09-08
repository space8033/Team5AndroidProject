package com.example.team5androidproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private List<Product> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_board, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(itemView, onItemClickListener);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Product product = list.get(position);
        holder.setData(product);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
    public Product getItem(int position){
        return list.get(position);
    }
    //목록 -> 상세 넘어가기
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}