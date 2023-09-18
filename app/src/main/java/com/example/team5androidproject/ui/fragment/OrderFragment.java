package com.example.team5androidproject.ui.fragment;

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
import com.example.team5androidproject.databinding.FragmentOrderBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class OrderFragment extends Fragment {
    private static final String TAG = "OrderFragment";
    private FragmentOrderBinding binding;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        /*initBtnBack();*/
        initBtnMypage();

        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    /*private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }*/

    private void initBtnMypage() {
        binding.btnMypage.setOnClickListener(v -> {
            navController.navigate(R.id.dest_mypage);
        });
    }
}