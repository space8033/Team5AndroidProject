package com.example.team5androidproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Inquiry;
import com.example.team5androidproject.ui.viewHolder.InquiryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class InquiryAdapter extends RecyclerView.Adapter<InquiryViewHolder> {
    List<Inquiry> list = new ArrayList<>();
    @NonNull
    @Override
    public InquiryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_inquriy, parent, false);
        InquiryViewHolder inquiryViewHolder = new InquiryViewHolder(itemView);

        return inquiryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InquiryViewHolder holder, int position) {
        Inquiry inquiry = list.get(position);
        holder.setData(inquiry);
    }

    @Override
    public int getItemCount() {
        if(list == null) {
            return 0;
        }
        return list.size();
    }

    public void setList(List<Inquiry> list) {
        this.list = list;
    }
}
