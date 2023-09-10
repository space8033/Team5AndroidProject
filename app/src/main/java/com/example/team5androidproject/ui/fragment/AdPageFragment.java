package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentAdPageBinding;

public class AdPageFragment extends Fragment {
    private static final String TAG = "AdPageFragment";
    private FragmentAdPageBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdPageBinding.inflate(getLayoutInflater());

        Bundle bundle = getArguments();
        int pageNo = bundle.getInt("pageNo");
        if(pageNo == 1) {
            binding.adView.setImageResource(R.drawable.mainslide1);
        }else if(pageNo == 2) {
            binding.adView.setImageResource(R.drawable.mainslide2);
        }else if(pageNo == 3) {
            binding.adView.setImageResource(R.drawable.mainslide3);
        }

        initUIByPageNo(pageNo);

        return binding.getRoot();
    }

    private void initUIByPageNo(int pageNo) {
        if(pageNo == 1) {
            binding.adView.setImageResource(R.drawable.mainslide1);
        }else if(pageNo == 2) {
            binding.adView.setImageResource(R.drawable.mainslide2);
        }else if(pageNo == 3) {
            binding.adView.setImageResource(R.drawable.mainslide3);
        }
    }
}