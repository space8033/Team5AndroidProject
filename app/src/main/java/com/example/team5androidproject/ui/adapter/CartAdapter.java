package com.example.team5androidproject.ui.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.DecimalFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
    private Button deleteAll;
    private TextView countCart;
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
            // 아이템의 체크박스 상태를 설정
            if (position >= 0 && position < selectedItems.length) {
                selectedItems[position] = isChecked;
            } else {
                // position 값이 유효한 범위를 벗어난 경우 오류 처리
                Log.e(TAG, "유효하지 않은 position 값: " + position);
            }

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
            deleteOneCartItem(cart.getCart_no());
        });

        deleteAll.setOnClickListener(v->{
            deleteSelectedItems();
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

    // for문이 돌면 dialog와 토스트가 그만큼 출력되므로 두개를 제외한 기본 삭제 메소드
    private void deleteCart(int cartNo) {

                Call<Void> callDelete = cartService.deleteOneCart(cartNo);
                callDelete.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            removeItem(cartNo);
                        } else {
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            }
    // 다중 삭제 메서드
    public void deleteSelectedItems() {
        List<Integer> itemsToDelete = new ArrayList<>();

        // 선택된 항목을 찾아서 삭제할 항목 리스트에 추가
        for (int i = 0; i < selectedItems.length; i++) {
            if (selectedItems[i]) {
                itemsToDelete.add(list.get(i).getCart_no());
            }
        }

        // 선택된 상품이 없는 경우 다이얼로그를 띄웁니다.
        if (itemsToDelete.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(selectPrice.getContext());
            builder.setTitle("선택한 상품 없음");
            builder.setMessage("삭제할 상품을 선택하지 않았습니다.");

            // 확인 버튼을 누를 때의 동작을 정의합니다.
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // 아무 동작도 하지 않습니다.
                }
            });

            // AlertDialog를 생성하고 표시합니다.
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }

        // 다중 삭제 여부를 묻는 다이얼로그 표시
        AlertDialog.Builder builder = new AlertDialog.Builder(selectPrice.getContext());
        builder.setTitle("삭제 확인");
        builder.setMessage("선택한 상품을 장바구니에서 삭제하시겠습니까?");

        // 확인 버튼을 누를 때의 동작을 정의합니다.
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 선택된 상품들을 삭제합니다.
                for (Integer cartNo : itemsToDelete) {
                    // 여기서 다이얼로그를 표시하지 않고 삭제 진행
                    Call<Void> callDelete = cartService.deleteOneCart(cartNo);
                    callDelete.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                removeItem(cartNo);
                                int remainingItemCount = list.size(); // 상품 하나 삭제
                                countCart.setText(remainingItemCount - checkedCartIds.size() + "개의 상품이 담겨있습니다");
                            } else {
                                // 삭제에 실패한 경우에 대한 처리를 추가할 수 있습니다.
                                Toast.makeText(selectPrice.getContext(), "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // 삭제에 실패한 경우에 대한 처리를 추가할 수 있습니다.
                            Toast.makeText(selectPrice.getContext(), "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                // 선택 항목 초기화
                Arrays.fill(selectedItems, false);
                checkedCartIds.clear();

                notifyDataSetChanged();

                selectNum.setText("총 0개"); // 선택된 항목 수 초기화
                selectPrice.setText("| 0원 결제하기"); // 선택된 항목 가격 초기화
                Toast.makeText(selectPrice.getContext(), "장바구니에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        // AlertDialog를 생성하고 표시합니다.
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // 개별 삭제 메서드
    private void deleteOneCartItem(int cartNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(selectPrice.getContext());
        builder.setTitle("삭제 확인");
        builder.setMessage("이 상품을 장바구니에서 삭제하시겠습니까?");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Call<Void> callDelete = cartService.deleteOneCart(cartNo);
                callDelete.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            removeItem(cartNo);
                            Toast.makeText(selectPrice.getContext(), "장바구니에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            int remainingItemCount = list.size() - 0; // 상품 하나 삭제
                            countCart.setText(remainingItemCount + "개의 상품이 담겨있습니다");
                        } else {
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

    //개별 삭제 메소드


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
            selectedItems = new boolean[list.size()]; // 아이템이 삭제되었으므로 배열 크기 업데이트
            Arrays.fill(selectedItems, false); // 모든 아이템을 선택 해제 상태로 초기화
            checkedCartIds.clear(); // 체크된 상품 ID 목록 초기화
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
        checkedCartIds.clear(); // 체크된 상품 ID 목록 초기화
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

    private int getCheckedItemsTotalPrice() {
        int totalPrice = 0;

        // 체크된 상품만큼만 반복
        for (int i = 0; i < selectedItems.length; i++) {
            if (selectedItems[i]) {
                int cartNo = list.get(i).getCart_no();

                // 해당 cartNo를 가진 상품을 찾아서 price 값을 추출
                for (Cart cartItem : list) {
                    if (cartItem.getCart_no() == cartNo) {
                        totalPrice += cartItem.getProduct_price() * cartItem.getCart_qty();
                        break; // 찾았으면 더 이상 반복할 필요 없음
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

    public void getDeleteAllBtn(Button deleteAll){
        this.deleteAll = deleteAll;
    }
    public void getCountCartTextview(TextView countCart) {this.countCart = countCart; }
}






