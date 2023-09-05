package com.example.team5androidproject.ui;

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

import com.example.team5androidproject.databinding.FragmentMyPageBinding;


public class MyPageFragment extends Fragment {
    private static final String TAG = "MyPageFragment";
    private FragmentMyPageBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        initBtnMain();
        initBtnDetail();

        return binding.getRoot();
    }

    private void initBtnMain() {
        binding.btnMain.setOnClickListener(v -> {
            navController.popBackStack(R.id.dest_main, false);
        });
    }

    private void initBtnDetail() {
        binding.btnDetail.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_mypage_to_dest_detail);
        });
    }
}