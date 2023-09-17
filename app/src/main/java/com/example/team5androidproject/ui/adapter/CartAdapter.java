package com.example.team5androidproject.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Cart;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.service.CartService;
import com.example.team5androidproject.ui.viewHolder.CartViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Cart> list = new ArrayList<>();
    private CheckBox allCheckBox; // 전체 체크박스
    private CartService cartService;
    private boolean[] selectedItems; // 각 아이템의 체크 상태 배열 (체크되면 true 아니면 false)
    public TextView selectedItemCountText;

    private static final String TAG = "CartAdapter";

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
        holder.qtyMinusButton.setOnClickListener(view ->decreaseQuantity(position));

        //아이템의 체크박스 상태를 설정
        holder.checkBox.setChecked(selectedItems[position]);

        // 체크된 아이템의 수량을 설정
        int selectedItemCount = getSelectedItemCount();
        /*selectedItemCountText.setText("선택된 상품: " + selectedItemCount + "개");*/

        //체크 박스의 클릭 리스너를 설정하여 체크 상태가 변경될 때 배열을 업데이트
        holder.checkBox.setOnClickListener(view -> {
            selectedItems[position] = holder.checkBox.isChecked(); //true, false 반환
            Log.i(TAG, "선택된 체크 박스의 값" + selectedItems[position]);
            checkSelectAll(); // 체크 박스 상태가 변경될 때마다 전체 선택 상태 업데이트
            Log.i(TAG, "선택된 상품의 갯수" + selectedItemCount);
        });

        allCheckBox.setOnClickListener(view ->{
            Log.i(TAG, "전체선택 버튼 클릭");
            toggleSelectAll();
            Log.i(TAG, "선택된 상품의 갯수" + selectedItemCount);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Cart> list) {
        this.list = list;
        notifyDataSetChanged(); // 데이터가 변경되면 RecyclerView를 갱신합니다.
        selectedItems = new boolean[list.size()]; // 체크 상태 배열 초기화
        Arrays.fill(selectedItems, false); // 모든 아이템을 선택 안 함으로 초기화
    }


    public void increaseQuantity(int position) {
        Cart cartItem = list.get(position);
        int newQuantity = cartItem.getCart_qty() + 1;
        cartItem.setCart_qty(newQuantity);
        notifyItemChanged(position);
        Log.i(TAG, "변경된 카트 수량" + cartItem.getCart_qty() + "해당 카트 번호" + cartItem.getCart_no());
    }

    // 수량을 감소시키는 메서드
    public void decreaseQuantity(int position) {
        Cart cartItem = list.get(position);
        int newQuantity = cartItem.getCart_qty() - 1;
        if (newQuantity >= 1) {
            cartItem.setCart_qty(newQuantity);
            notifyItemChanged(position);
            Log.i(TAG, "변경된 카트 수량" + newQuantity + "해당 카트 번호" + cartItem.getCart_no());
        }
    }
    public void toggleSelectAll() {
        boolean allSelected = true; // 전체 선택 버튼이 눌려 있으면
        for (boolean selectedItem : selectedItems) {
            if (!selectedItem) {
                allSelected = false;
                break;
            }
        }
        // 모든 항목이 체크되지 않았다면 모두 체크로 변경
        if (!allSelected) {
            Arrays.fill(selectedItems, true);
        } else {
            Arrays.fill(selectedItems, false); // 모든 항목이 체크되어 있었다면 모두 체크 해제로 변경
        }

        notifyDataSetChanged(); // Adapter 갱신
    }

    // 전체 선택 상태 업데이트 메서드
    private void checkSelectAll() {
        boolean allSelected = true; //전체 선택 버튼이 눌려 있으면
        for (boolean selectedItem : selectedItems) { // checkbox 의 수만큼 for문이 돌아간다.
            if (!selectedItem) {
                allSelected = false;
                break;
            }
        }
        allCheckBox.setChecked(allSelected);
    }

    public void getAllCheckBox(CheckBox allCheckBox){
        this.allCheckBox = allCheckBox;
    }

    public int getSelectedItemCount() {
        int count = 0;
        for (boolean selectedItem : selectedItems) {
            if (selectedItem) {
                count++;
            }
        }
        return count;
    }
}




