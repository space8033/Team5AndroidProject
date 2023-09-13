package com.example.team5androidproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Cart;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.ui.viewHolder.CartViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Cart> list = new ArrayList<>();

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_cart, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(itemView);

        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = list.get(position);
        holder.setData(cart);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Cart> list) {
        this.list = list;
    }

}
