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
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.MyPage;
import com.example.team5androidproject.service.MemberService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.MyPagePagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
        initBtnPoint();
        initBtnCoupon();
        initBtnReview();
        initBtnInquiry();
        initFloatButton();
        hideButton();
        initBtnLogout();
        initMyPageView();

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

    private void initBtnPoint() {
        binding.layoutPoint.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_mypage_to_dest_point);
        });
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

    private void initBtnInquiry() {
        binding.layoutInquriy.setOnClickListener(v -> {
            binding.viewpagerMypage.setCurrentItem(2);
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

    private void initBtnLogout() {
        binding.btnLogout.setOnClickListener(v -> {
            AppKeyValueStore.remove(getContext(), "userId");
            AppKeyValueStore.remove(getContext(), "password");

            BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
            bottomNavigationView.getMenu().findItem(R.id.dest_login).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.dest_mypage).setVisible(false);

            navController.navigate(R.id.dest_main);
        });
    }

    private void initMyPageView() {
        String userId = AppKeyValueStore.getValue(getContext(), "userId");
        MemberService memberService = ServiceProvider.getMemberService(getContext());
        Call<MyPage> call = memberService.mypage(userId);
        call.enqueue(new Callback<MyPage>() {
            @Override
            public void onResponse(Call<MyPage> call, Response<MyPage> response) {
                MyPage myPage = response.body();
                binding.txtName.setText(myPage.getName());
                binding.txtCreatedAt.setText(myPage.getCreated_at());
                binding.txtPoint.setText(myPage.getPoint() + "P");
                binding.txtCoupon.setText(myPage.getCouponCount() + "장");
                binding.txtReview.setText(myPage.getReviewCount() + "건");
                binding.txtInquriy.setText(myPage.getInquiryCount() + "건");
                MemberService.loadImage(userId, binding.profileImage);
            }

            @Override
            public void onFailure(Call<MyPage> call, Throwable t) {

            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        requireActivity().findViewById(R.id.bottom_navigation_view).setVisibility(View.VISIBLE);
    }
}