package com.example.team5androidproject.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentOrderBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.Coupon;
import com.example.team5androidproject.dto.Login;
import com.example.team5androidproject.dto.Order;
import com.example.team5androidproject.dto.OrderUser;
import com.example.team5androidproject.service.CartService;
import com.example.team5androidproject.service.OrderService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.OrderAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment  {
    private static final String TAG = "OrderFragment";
    private FragmentOrderBinding binding;
    private NavController navController;
    private List<Integer> cart_no; // 전역변수로 선언
    private int firstCart;
    private OrderAdapter orderAdapter; // OrderAdapter 객체 선언
    private int totalPayProductPrice;







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        //int cartNo = bundle.getInt("cartIds");
        Log.i(TAG, "onCreateView:" + bundle);
        cart_no = bundle.getIntegerArrayList("cartIds");
        int firstCart = cart_no.get(0);
        Log.i(TAG, "카트아이디들" + cart_no);
        Coupon coupon = (Coupon)getArguments().getSerializable("coupon");



        if(coupon != null){
            int couponPrice = coupon.getCoupon_value();
            binding.couponPrice.setText("- " + couponPrice + " 원");
            binding.lastCoupon.setText("- " + couponPrice + " 원");
            /*int finalPrice = orderAdapter.getTotalPayProductPrice();
            binding.lastPrice.setText(finalPrice - couponPrice);*/
        }

        /*initBtnBack();*/
        initBtnMypage();
        /*initOderPage();*/
        initOrderUser(firstCart);
        initRecyclerView();
        intiClickUsePoint();
        intiPayCoupon();


        return binding.getRoot();
    }



    private void initRecyclerView() {

        OrderService orderService = ServiceProvider.getOrderService(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.orderRecyclerView.setLayoutManager(linearLayoutManager);

        orderAdapter = new OrderAdapter(); // orderAdapter 초기화

        Call<List<Order>> call = orderService.getOrderInfos(cart_no);
        Log.i(TAG, "카트의 배열 orderService 사용" + cart_no);
        Log.i(TAG, "call 메소드의 값: " + call.toString());
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orderList = response.body();
                orderAdapter.setList(orderList);
                binding.orderRecyclerView.setAdapter(orderAdapter);

                // TotalPayProductPrice 가져오기
                 totalPayProductPrice = orderAdapter.getTotalPayProductPrice();
                String coupon1 = binding.couponPrice.getText().toString();
                String coupon2 =coupon1.replaceAll("[^0-9]", "");

                int couponPrice = Integer.parseInt(coupon2);
                int totalPayProductPrice = orderAdapter.getTotalPayProductPrice();
                binding.payPrice.setText(totalPayProductPrice+"원");
                int newTotalPrice = totalPayProductPrice - couponPrice;
                binding.lastPrice.setText(String.valueOf(newTotalPrice+ " 원")); //총가격
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.i(TAG, "recyclerView 불러오기 실패");
            }
        });




    }


    /*private void initOderPage(List<Integer> selectedCartNos) {
        String userId = AppKeyValueStore.getValue(getContext(), "userId");
        OrderService orderService = ServiceProvider.getOrderService(getContext());
        Call<OrderUser> call = orderService.getOrderUser(cart_no);
        call.enqueue(new Callback<MyPage>() {
            @Override
            public void onResponse(Call<MyPage> call, Response<MyPage> response) {
                MyPage myPage = response.body();
                binding.txtName.setText(myPage.getName());
                binding.txtCreatedAt.setText(myPage.getCreated_at());
                binding.txtPoint.setText(String.valueOf(myPage.getPoint()) + "P");
                binding.txtCoupon.setText(String.valueOf(myPage.getCouponCount()) + "장");
                binding.txtReview.setText(String.valueOf(myPage.getReviewCount()) + "건");
                binding.txtInquriy.setText(String.valueOf(myPage.getInquiryCount()) + "건");
                MemberService.loadImage(userId, binding.profileImage);
            }

            @Override
            public void onFailure(Call<MyPage> call, Throwable t) {

            }
        });
    }*/

    @Override
    public void onPause() {
        super.onPause();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    /*private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }*/
    private void intiPayCoupon() {
        binding.useCouponBtn.setOnClickListener(c->{
           Bundle bundle = getArguments();
           navController.navigate(R.id.dest_pay_coupon, bundle);
        });
        String coupon1 = binding.couponPrice.getText().toString();
        String coupon2 =coupon1.replaceAll("[^0-9]", "");

        int couponPrice = Integer.parseInt(coupon2);
        int newTotalPrice = totalPayProductPrice - couponPrice;

        binding.lastPrice.setText(String.valueOf(newTotalPrice+ " 원")); //총가격
    }




    private void initBtnMypage() {
        binding.btnMypage.setOnClickListener(v -> {
            navController.navigate(R.id.dest_mypage);
        });
    }

    private void initOrderUser(int firstCart) {
        String userId = AppKeyValueStore.getValue(getContext(), "userId");
        OrderService orderService = ServiceProvider.getOrderService(getContext());
        Call<OrderUser> call = orderService.getOrderItems(firstCart);
        Log.i(TAG, "첫번째 카트 no" + firstCart);
        call.enqueue(new Callback<OrderUser>() {
            @Override
            public void onResponse(Call<OrderUser> call, Response<OrderUser> response) {
                OrderUser orderUser = response.body();
                Log.i(TAG, "콜 메소드 실행");
                binding.orderName.setText(String.valueOf(orderUser.getUsers_name()));
                Log.i(TAG, "유저의 이름: " + orderUser.getUsers_name());
                binding.orderEmail.setText(String.valueOf(orderUser.getUsers_email()));
                Log.i(TAG, "유저의 이름: " + orderUser.getUsers_email());
                binding.orderPhone.setText(String.valueOf(orderUser.getUsers_phone()));
                Log.i(TAG, "유저의 이름: " + orderUser.getUsers_phone());
                binding.userPoint.setText("보유포인트 "+String.valueOf(orderUser.getPoint()));
                Log.i(TAG, "유저의 이름: " + orderUser.getPoint());
            }

            @Override
            public void onFailure(Call<OrderUser> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }

    private void intiClickUsePoint() {
        binding.usePointBtn.setOnClickListener(v->{
            String userPointText = binding.userPoint.getText().toString();
            String numericPart = userPointText.replaceAll("[^0-9]", "");
            int maxPointToUse = Integer.parseInt(numericPart);
            String enteredPoint = binding.editTextPoint.getText().toString();

            Log.i(TAG, "사용자 보유 포인트: " + userPointText);
            Log.i(TAG, "최대 사용 포인트: " + maxPointToUse);
            Log.i(TAG, "입력된 숫자값" + enteredPoint);

            try {
                int enteredPointValue = Integer.parseInt(enteredPoint);

                if (enteredPointValue > maxPointToUse) {
                    // 사용하려는 적립금이 보유 적립금을 초과하는 경우
                    showDialog("적립금 초과", "입력한 적립금이 보유 적립금을 초과합니다.");
                } else if (enteredPointValue < 0) {
                    showDialog("잘못된 금액", "0 이상의 값을 입력하세요.");
                } else {
                    // 적립금 사용 가능한 범위 내의 값을 입력한 경우
                    int totalPayProductPrice = orderAdapter.getTotalPayProductPrice();
                    String coupon1 = binding.couponPrice.getText().toString();
                    String coupon2 =coupon1.replaceAll("[^0-9]", "");

                    int couponPrice = Integer.parseInt(coupon2);
                    int newTotalPrice = totalPayProductPrice - enteredPointValue - couponPrice;

                    binding.lastPoint.setText(String.valueOf("- "+enteredPointValue+" 원")); //최종 적립금 가격
                    binding.lastPrice.setText(String.valueOf(newTotalPrice+ " 원")); //총가격
                }
            } catch (NumberFormatException e) {
                // 유효하지 않은 숫자가 입력된 경우
                showDialog("유효하지 않은 값", "숫자 값을 입력하세요.");
            }
        });
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.editTextPoint.setText("0");
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}