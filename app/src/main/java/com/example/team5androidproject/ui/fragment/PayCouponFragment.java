package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentCouponBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.Coupon;
import com.example.team5androidproject.service.CouponService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.CouponAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayCouponFragment extends Fragment {
    private static final String TAG = "CouponFragment";
    private FragmentCouponBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCouponBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        CouponService couponService = ServiceProvider.getCouponService(getContext());

        String id = AppKeyValueStore.getValue(getContext(), "userId");
        //쿠폰수 받아오기
        Call<Integer> callCount = couponService.getCouponCount(id);
        callCount.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                binding.txtCouponCount.setText(String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

        //쿠폰 리사이클러뷰 가져오기
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );

        binding.couponImageRecycler.setLayoutManager(layoutManager);

        CouponAdapter couponAdapter = new CouponAdapter();

        Call<List<Coupon>> call = couponService.getCouponByUser(id);

        call.enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                List<Coupon> list = response.body();
                couponAdapter.setList(list);
                binding.couponImageRecycler.setAdapter(couponAdapter);
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {}
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}