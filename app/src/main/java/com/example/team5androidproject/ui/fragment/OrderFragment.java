package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentOrderBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.MyPage;
import com.example.team5androidproject.dto.OrderUser;
import com.example.team5androidproject.service.MemberService;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        /*initBtnBack();*/
        initBtnMypage();
        /*initOderPage();*/

        return binding.getRoot();
    }

    public interface CartSelectionListener {
        void onCartItemSelectionChanged(List<Integer> selectedCartNos); //상품의 cart_no를 전달하기 위한 메서드
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
}