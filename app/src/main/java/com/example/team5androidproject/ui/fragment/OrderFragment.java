package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentOrderBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.OrderUser;
import com.example.team5androidproject.service.OrderService;
import com.example.team5androidproject.service.ServiceProvider;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        int cartNo = bundle.getInt("cartIds");
        Log.i(TAG, "onCreateView:" + bundle);
        List<Integer> cart_no = bundle.getIntegerArrayList("cartIds");
        int firstCart = cart_no.get(0);
        Log.i(TAG, "카트아이디들" + cart_no);


        /*initBtnBack();*/
        initBtnMypage();
        /*initOderPage();*/
        initOrderUser(firstCart);



        return binding.getRoot();
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

}