package com.example.team5androidproject.ui.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.team5androidproject.ui.fragment.AdPageFragment;

public class AdPagerAdapter extends FragmentStateAdapter {


    public AdPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        AdPageFragment adPageFragment = new AdPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNo", position + 1);
        adPageFragment.setArguments(bundle);

        return adPageFragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
