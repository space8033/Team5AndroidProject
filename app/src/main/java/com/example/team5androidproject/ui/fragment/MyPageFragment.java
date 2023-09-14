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

import com.example.team5androidproject.databinding.FragmentMyPageBinding;
import com.example.team5androidproject.ui.adapter.MyPagePagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MyPageFragment extends Fragment {
    private static final String TAG = "MyPageFragment";
    private FragmentMyPageBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initMyPagePagerView();
        initBtnCoupon();
        initBtnReview();
        initFloatButton();
        hideButton();
        initBtnLogin();

        return binding.getRoot();
    }

    private void initMyPagePagerView() {
        MyPagePagerAdapter myPagePagerAdapter = new MyPagePagerAdapter(this);
        binding.viewpagerMypage.setAdapter(myPagePagerAdapter);
        binding.viewpagerMypage.setUserInputEnabled(false);

        //탭 레이아웃
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                binding.tabLayout, binding.viewpagerMypage, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0) {
                    tab.setText("주문내역");
                }else if(position == 1) {
                    tab.setText("리뷰관리");
                }else if(position == 2) {
                    tab.setText("문의관리");
                }
            }
        });
        tabLayoutMediator.attach();
    }

    private void initBtnCoupon() {
        binding.layoutCoupon.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_mypage_to_dest_coupon);
        });
    }

    private void initBtnReview() {
        binding.layoutReview.setOnClickListener(v -> {
            binding.viewpagerMypage.setCurrentItem(1);
        });
    }
    private void initFloatButton() {
        binding.mypageNestedScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > oldScrollY) {
                    showButton();
                }else {
                    hideButton();
                }
            }
        });

        binding.btnMypageFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mypageNestedScroll.smoothScrollTo(0, 0);
            }
        });
    }

    private void hideButton() {
        binding.btnMypageFloat.hide();
    }

    private void showButton() {
        binding.btnMypageFloat.show();
    }

    private void initBtnLogin() {
        binding.btnLogin.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_mypage_to_dest_login);
        });
    }
}