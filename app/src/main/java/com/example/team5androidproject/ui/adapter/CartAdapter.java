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
    private boolean isAllSelected = false; //전체 선택 여부를 나타내는 변수


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

        // "수량 증가" 버튼 클릭 이벤트 처리
        holder.qtyPlusButton.setOnClickListener(view -> increaseQuantity(position));
        // "수량 감소" 버튼 클릭 이벤트 처리
        holder.qtyMinusButton.setOnClickListener(view -> decreaseQuantity(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Cart> list) {
        this.list = list;
        notifyDataSetChanged(); // 데이터가 변경되면 RecyclerView를 갱신합니다.
    }

    // 전체 선택 상태 설정
    public void setAllSelected(boolean isAllSelected) {
        this.isAllSelected = isAllSelected;
        for (Cart cart : list) {
            cart.setSelected(isAllSelected); // 모든 아이템의 선택 상태를 설정합니다.
        }
        notifyDataSetChanged(); // 데이터가 변경되면 RecyclerView를 갱신합니다.
    }

    // 전체 선택 상태 반환
    public boolean isAllSelected() {
        return isAllSelected;
    }

    //수량을 증가시키는 메서드
    public void increaseQuantity(int position) {
        Cart cartItem = list.get(position);
        int newQuantity = cartItem.getCart_qty() + 1;
        cartItem.setCart_qty(newQuantity);
        notifyItemChanged(position);
    }

    // 수량을 감소시키는 메서드
    public void decreaseQuantity(int position) {
        Cart cartItem = list.get(position);
        int newQuantity = cartItem.getCart_qty() - 1;
        if (newQuantity >= 1) {
            cartItem.setCart_qty(newQuantity);
            notifyItemChanged(position);
        }
    }
}



