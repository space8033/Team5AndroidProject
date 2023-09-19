package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.team5androidproject.databinding.FragmentDetailImgBinding;
import com.example.team5androidproject.dto.ProductDetail;
import com.example.team5androidproject.service.ProductService;


public class DetailImgFragment extends Fragment {

    private static final String TAG = "DetailImgFragment";
    private FragmentDetailImgBinding binding;
    private int product_no;
    public DetailImgFragment(int product_no){
        this.product_no = product_no;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailImgBinding.inflate(getLayoutInflater());

        ImageView imageView = binding.detailImg;
        Log.i(TAG, "product_no : " + product_no);
        ProductService.loadDetailImgDetail(product_no, imageView);

        return binding.getRoot();
    }
}