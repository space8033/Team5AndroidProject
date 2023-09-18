package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentPointBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.Point;
import com.example.team5androidproject.service.MemberService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.PointAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointFragment extends Fragment {
    private static final String TAG = "PointFragment";
    private FragmentPointBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPointBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        MemberService memberService = ServiceProvider.getMemberService(getContext());
        String id = AppKeyValueStore.getValue(getContext(), "userId");

        Call<Integer> totalPoint = memberService.totalPoint(id);
        totalPoint.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                binding.totalPoint.setText(response.body() + "P");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );

        binding.pointRecycler.setLayoutManager(linearLayoutManager);

        Call<List<Point>> call = memberService.point(id);
        PointAdapter pointAdapter = new PointAdapter();
        call.enqueue(new Callback<List<Point>>() {
            @Override
            public void onResponse(Call<List<Point>> call, Response<List<Point>> response) {
                List<Point> list = response.body();
                pointAdapter.setList(list);
                binding.pointRecycler.setAdapter(pointAdapter);
            }

            @Override
            public void onFailure(Call<List<Point>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}