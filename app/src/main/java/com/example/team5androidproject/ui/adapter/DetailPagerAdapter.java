package com.example.team5androidproject.ui.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.team5androidproject.ui.fragment.DetailImgFragment;
import com.example.team5androidproject.ui.fragment.DetailReviewFragment;
import com.example.team5androidproject.ui.fragment.ReviewFragment;

public class DetailPagerAdapter extends FragmentStateAdapter {
    private static final String TAG = "DetailPagerAdapter";
    private int product_no;

    public DetailPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Fragment();
        if(position == 0) {
            fragment = new DetailImgFragment(product_no);
            Log.i(TAG, "DetailFragment_product_no : " + product_no);
        }else if(position == 1) {
            fragment = new DetailReviewFragment(product_no);
            Log.i(TAG, "DetailFragment_product_no : " + product_no);
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
