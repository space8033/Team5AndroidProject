package com.example.team5androidproject.ui.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.team5androidproject.ui.fragment.InquiryFragment;
import com.example.team5androidproject.ui.fragment.OrderHistoryFragment;
import com.example.team5androidproject.ui.fragment.ReviewFragment;

public class MyPagePagerAdapter extends FragmentStateAdapter {
    public MyPagePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Fragment();
        if(position == 0) {
            fragment = new OrderHistoryFragment();
        }else if(position == 1) {
            fragment = new ReviewFragment();
        }else if(position == 2) {
            fragment = new InquiryFragment();
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
