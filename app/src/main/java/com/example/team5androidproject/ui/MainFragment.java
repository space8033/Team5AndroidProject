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
import com.example.team5androidproject.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private FragmentMainBinding binding;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);

        initBtnSearch();
        initBtnDetail();
        initBtnMypage();
        initBtnCart();
        initBtnLogin();


        return binding.getRoot();


    }

    private void initBtnSearch() {
        binding.btnSearch.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_main_to_dest_search);
        });
    }

    private void initBtnDetail() {
        binding.btnDetail.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_main_to_dest_detail);
        });
    }

    private void initBtnMypage() {
        binding.btnMypage.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_main_to_dest_mypage);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_main_to_dest_cart);
        });
    }

    private void initBtnLogin() {
        binding.btnLogin.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_main_to_dest_login);
        });
    }
}