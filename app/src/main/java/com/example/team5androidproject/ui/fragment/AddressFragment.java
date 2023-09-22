package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentAddressBinding;

public class AddressFragment extends Fragment {
    private static final String TAG = "AddressFragment";
    private FragmentAddressBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddressBinding.inflate(inflater);

        initBtnAddAddress();

        return binding.getRoot();
    }

    private void initBtnAddAddress() {
        binding.btnAddAddress.setOnClickListener(v -> {
            navController.navigate(R.id.dest_add_address);
        });
    }
}