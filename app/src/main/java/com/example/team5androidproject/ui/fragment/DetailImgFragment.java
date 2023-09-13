package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentDetailImgBinding;


public class DetailImgFragment extends Fragment {

    private static final String TAG = "DetailImgFragment";
    private FragmentDetailImgBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailImgBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}