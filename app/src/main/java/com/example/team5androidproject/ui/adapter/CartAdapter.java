package com.example.team5androidproject.ui.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.DecimalFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Cart;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.service.CartService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.viewHolder.CartViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Cart> list = new ArrayList<>();
    private CheckBox allCheckBox; // 전체 체크박스
    private CartService cartService;
    private boolean[] selectedItems; // 각 아이템의 체크 상태 배열 (체크되면 true 아니면 false)
    public TextView selectedItemCountText;
    private List<Integer> checkedCartIds = new ArrayList<>(); //체크된 상품의 카트 번호가 들어갈 List 선언
    private TextView selectNum;
    private TextView selectPrice;
    DecimalFormat df =new DecimalFormat("#,###");

    private static final String TAG = "CartAdapter";

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_cart, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(itemView);
        cartService = ServiceProvider.getCartService(itemView.getContext());

        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = list.get(position);
        holder.setData(cart);

        // "수량 증가" 버튼 클릭 이벤트 처리
        holder.qtyPlusButton.setOnClickListener(view -> {
            increaseQuantity(position);
            updateQuantity(cart.getCart_no(),cart.getCart_qty());
            getCheckedItemsTotalPrice();
            selectPrice.setText( "| " + String.valueOf(df.format(getCheckedItemsTotalPrice())) + "원 결제하기");
        });
        // "수량 감소" 버튼 클릭 이벤트 처리
        holder.qtyMinusButton.setOnClickListener(view -> {
            decreaseQuantity(position);
            updateQuantity(cart.getCart_no(),cart.getCart_qty());
            getCheckedItemsTotalPrice();
            selectPrice.setText( "| " + String.valueOf(df.format(getCheckedItemsTotalPrice())) + "원 결제하기");
        });
        // 아이템의 체크박스 상태를 설정
        holder.checkBox.setChecked(selectedItems[position]);

        // 체크 박스의 클릭 리스너를 설정하여 체크 상태가 변경될 때 배열을 업데이트
        holder.checkBox.setOnClickListener(view -> {
            boolean isChecked = holder.checkBox.isChecked();
            selectedItems[position] = isChecked;

            if (isChecked) {
                // 체크된 경우 해당 상품의 cartId를 리스트에 추가
                checkedCartIds.add(cart.getCart_no());
            } else {
                // 체크가 해제된 경우 해당 상품의 cartId를 리스트에서 제거
                checkedCartIds.removeIf(cartId -> cartId == cart.getCart_no());
            }
            checkSelectAll();
            Log.i(TAG, "체크돈 상품의 종류" + checkedCartIds);
            Log.i(TAG, "체크된 상품의 갯수"+ checkedCartIds.size());
            selectNum.setText("총 " + checkedCartIds.size() + "개");
            getCheckedItemsTotalPrice();
            selectPrice.setText( "| " + String.valueOf(df.format(getCheckedItemsTotalPrice())) + "원 결제하기");
            Log.i(TAG, "체크된 상품의 총 가격의 합" + getCheckedItemsTotalPrice());
        });

        allCheckBox.setOnClickListener(view -> {
            Log.i(TAG, "전체선택 버튼 클릭");
            int newSelectedItemCount = checkedCartIds.size();
            toggleSelectAll();
            Log.i(TAG, "체크돈 상품의 종류" + checkedCartIds);
            Log.i(TAG, "체크된 상품의 갯수"+ checkedCartIds.size());
            selectNum.setText("총" + checkedCartIds.size() + "개");
            getCheckedItemsTotalPrice();
            Log.i(TAG, "체크된 상품의 총 가격의 합" + getCheckedItemsTotalPrice());
            selectPrice.setText( "| " + String.valueOf(df.format(getCheckedItemsTotalPrice())) + "원 결제하기");
        });

        holder.deleteOneButton.setOnClickListener(v->{
            deleteOneCart(cart.getCart_no());
        });
    }

    private void updateQuantity(int cartNo, int cartQty) {
        Call<Void> callUpdate = cartService.updateCart(cartNo, cartQty);
        callUpdate.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        Log.i(TAG, "업데이트 실행");
    }

    private void deleteOneCart(int cartNo) {
        // AlertDialog를 생성하여 삭제 확인 메시지를 표시합니다.
        AlertDialog.Builder builder = new AlertDialog.Builder(selectPrice.getContext());
        builder.setTitle("삭제 확인");
        builder.setMessage("이 상품을 장바구니에서 삭제하시겠습니까?");

        // 확인 버튼을 누를 때의 동작을 정의합니다.
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 사용자가 확인 버튼을 누르면 해당 상품을 서버에서 삭제합니다.
                Call<Void> callDelete = cartService.deleteOneCart(cartNo);
                callDelete.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // 삭제가 성공하면 RecyclerView에서도 해당 아이템을 삭제합니다.
                            removeItem(cartNo);
                            // 삭제가 완료되면 Toast 메시지를 표시합니다.
                            Toast.makeText(selectPrice.getContext(), "장바구니에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            // 삭제에 실패한 경우에 대한 처리를 추가할 수 있습니다.
                            Toast.makeText(selectPrice.getContext(), "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        // 취소 버튼을 누를 때의 동작을 정의합니다.
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 사용자가 취소 버튼을 누른 경우 아무 동작도 하지 않습니다.
            }
        });

        // AlertDialog를 생성하고 표시합니다.
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // RecyclerView에서 아이템을 삭제하는 메서드
    private void removeItem(int cartNo) {
        int position = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCart_no() == cartNo) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Cart> list) {
        this.list = list;
        selectedItems = new boolean[list.size()]; // 선택 상태 배열 크기를 리스트 크기와 동일하게 설정
        Arrays.fill(selectedItems, false); // 모든 아이템을 선택 안 함으로 초기화
        notifyDataSetChanged(); // 데이터가 변경되면 RecyclerView를 갱신합니다.
    }

    public void increaseQuantity(int position) {
        Cart cartItem = list.get(position);
        int newQuantity = cartItem.getCart_qty() + 1;
        cartItem.setCart_qty(newQuantity);
        notifyItemChanged(position);
        getCheckedItemsTotalPrice();
        Log.i(TAG, "변경된 카트 수량" + newQuantity + "해당 카트 번호" + cartItem.getCart_no() + "해당 카드의 변경된 가격" + cartItem.getProduct_price()* cartItem.getCart_qty());
        Log.i(TAG, "체크된 상품의 총 가격의 합" + getCheckedItemsTotalPrice());
    }

    // 수량을 감소시키는 메서드
    public void decreaseQuantity(int position) {
        Cart cartItem = list.get(position);
        int newQuantity = cartItem.getCart_qty() - 1;
        if (newQuantity >= 1) {
            cartItem.setCart_qty(newQuantity);
            notifyItemChanged(position);
            getCheckedItemsTotalPrice();
            Log.i(TAG, "변경된 카트 수량" + newQuantity + "해당 카트 번호" + cartItem.getCart_no() + "해당 카드의 변경된 가격" + cartItem.getProduct_price()* cartItem.getCart_qty());
            Log.i(TAG, "체크된 상품의 총 가격의 합" + getCheckedItemsTotalPrice());
        }
    }
    public void toggleSelectAll() {
        boolean allSelected = true; // 전체 선택 버튼이 눌려 있으면

        // 모든 아이템을 체크로 변경하고 상품 ID를 추가
        for (int i = 0; i < list.size(); i++) {
            if (!selectedItems[i]) {
                allSelected = false;
                selectedItems[i] = true; // 모든 아이템을 체크로 변경
                int cartId = list.get(i).getCart_no();
                if (!checkedCartIds.contains(cartId)) {
                    checkedCartIds.add(cartId); // 체크되지 않은 상품의 ID를 추가
                }
            }
        }

        // 전체 선택 버튼이 이미 눌려 있었으면 모든 아이템의 체크를 해제
        if (allSelected) {
            Arrays.fill(selectedItems, false); // 모든 아이템을 체크 해제로 변경
            checkedCartIds.clear(); // 모든 상품 ID를 제거
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

    private int getCheckedItemsTotalPrice(){
        int totalPrice = 0;
        for (int i = 0; i < selectedItems.length; i++) {
            if (selectedItems[i]) {
                // 체크된 상품인 경우 해당 상품의 cart_no로부터 price 값을 추출
                int cartNo = list.get(i).getCart_no();
                // 해당 cartNo를 가진 상품을 찾아서 price 값을 추출
                for (Cart cartItem : list) {
                    if (cartItem.getCart_no() == cartNo) {
                        totalPrice += cartItem.getProduct_price() * cartItem.getCart_qty();
                        break;
                    }
                }
            }
        }
        return totalPrice;
    }

    //체크박스를 프래그먼트에서 선언후 받아온다.
    public void getAllCheckBox(CheckBox allCheckBox) {
        this.allCheckBox = allCheckBox;
        // null 체크 추가
        if (allCheckBox != null) {
            allCheckBox.setOnClickListener(view -> {
                Log.i(TAG, "전체선택 버튼 클릭");
                int newSelectedItemCount = checkedCartIds.size();
                toggleSelectAll();
                Log.i(TAG, "체크돈 상품의 종류" + checkedCartIds);
                Log.i(TAG, "체크된 상품의 갯수" + checkedCartIds.size());
                selectNum.setText("총" + checkedCartIds.size() + "개");
                getCheckedItemsTotalPrice();
                Log.i(TAG, "체크된 상품의 총 가격의 합" + getCheckedItemsTotalPrice());
                selectPrice.setText( "| " + String.valueOf(df.format(getCheckedItemsTotalPrice())) + "원 결제하기");
            });
        }
    }


    //선택된 수량을 나타내는 TextView를 프래그먼트에서 받아온다.
    public void getSelectNumTextView(TextView selectNum){
        this.selectNum = selectNum;
    }

    public void getSelectPriceTextView(TextView selectPrice){
        this.selectPrice = selectPrice;
    }
}






