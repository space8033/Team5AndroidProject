package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentOrderHistoryBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.OrderHistory;
import com.example.team5androidproject.dto.Review;
import com.example.team5androidproject.service.OrderService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.OrderHistoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryFragment extends Fragment {
    private static final String TAG = "OrderHistoryFragment";
    private FragmentOrderHistoryBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(getLayoutInflater());
        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.outerRecyclerView.setLayoutManager(linearLayoutManager);
        OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter();
        String id = AppKeyValueStore.getValue(getContext(), "userId");
        OrderService orderService = ServiceProvider.getOrderService(getContext());
        Call<List<String>> dates = orderService.getDates(id);
        dates.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> dateList = response.body();
                orderHistoryAdapter.setList(dateList);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
            }
        });
        Call<List<OrderHistory>> histories = orderService.history(id);
        histories.enqueue(new Callback<List<OrderHistory>>() {
            @Override
            public void onResponse(Call<List<OrderHistory>> call, Response<List<OrderHistory>> response) {
                List<OrderHistory> historyList = response.body();
                orderHistoryAdapter.setHistoryList(historyList);
                binding.outerRecyclerView.setAdapter(orderHistoryAdapter);
            }

            @Override
            public void onFailure(Call<List<OrderHistory>> call, Throwable t) {
            }
        });

    }
}