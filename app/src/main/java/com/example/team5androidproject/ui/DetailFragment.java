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

import com.example.team5androidproject.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);

        initBtnOrder();
        initBtnCart();
        initBtnLogin();
        initBtnBack();

        return binding.getRoot();
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnLogin() {
        binding.btnLogin.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_detail_to_dest_login);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_detail_to_dest_cart);
        });
    }

    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_detail_to_dest_order);
        });
    }
}