package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentCouponBinding;

public class CouponFragment extends Fragment {
    private static final String TAG = "CouponFragment";
    private FragmentCouponBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon, container, false);
    }

    @Override
    public void onStop() {

        super.onStop();
    }
}