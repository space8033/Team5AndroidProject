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
import com.example.team5androidproject.databinding.FragmentDetailReviewBinding;
import com.example.team5androidproject.databinding.FragmentReviewBinding;
import com.example.team5androidproject.dto.Review;
import com.example.team5androidproject.service.ReviewService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.DetailReviewAdapter;
import com.example.team5androidproject.ui.adapter.ReviewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailReviewFragment extends Fragment {
    private static final String TAG = "DetailReviewFragment";
    private FragmentDetailReviewBinding binding;
    private NavController navController;
    private int product_no;
    public DetailReviewFragment(int product_no){
        this.product_no = product_no;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailReviewBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);


        initRecyclerView();


        return binding.getRoot();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL,false
        );

        binding.reviewRecycler.setLayoutManager(linearLayoutManager);

        DetailReviewAdapter detailReviewAdapter = new DetailReviewAdapter();

        ReviewService reviewService = ServiceProvider.getReviewService(getContext());
        Call<List<Review>> call = reviewService.getReviewByProductNo(product_no);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                List<Review> list = response.body();
                if(list.isEmpty()){
                    binding.tvNoReviews.setVisibility(View.VISIBLE);
                }else {
                    detailReviewAdapter.setList(list);
                    binding.reviewRecycler.setAdapter(detailReviewAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
    }
}