package com.example.team5androidproject.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.team5androidproject.ui.fragment.DetailImgFragment;
import com.example.team5androidproject.ui.fragment.DetailReviewFragment;
import com.example.team5androidproject.ui.fragment.ReviewFragment;

public class DetailPagerAdapter extends FragmentStateAdapter {

    public DetailPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Fragment();
        if(position == 0) {
            fragment = new DetailImgFragment();
        }else if(position == 1) {
            fragment = new DetailReviewFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
