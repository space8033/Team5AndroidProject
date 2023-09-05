package com.example.team5androidproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.databinding.FragmentCartBinding;


public class CartFragment extends Fragment {
    private static final String TAG = "CartFragment";
    private FragmentCartBinding binding;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}