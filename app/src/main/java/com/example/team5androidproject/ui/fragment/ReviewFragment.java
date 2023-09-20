package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentReviewBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.ProductDetail;
import com.example.team5androidproject.dto.Review;
import com.example.team5androidproject.service.DataTransfer;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.service.ReviewService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.ReviewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFragment extends Fragment {
    private static final String TAG = "ReviewFragment";
    private FragmentReviewBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReviewBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        String userId = AppKeyValueStore.getValue(getContext(), "userId");
        binding.txtUserId.setText(userId);

        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL,false
        );

        binding.reviewRecycler.setLayoutManager(linearLayoutManager);

        ReviewAdapter reviewAdapter = new ReviewAdapter();
        String id = AppKeyValueStore.getValue(getContext(), "userId");
        ReviewService reviewService = ServiceProvider.getReviewService(getContext());
        Call<List<Review>> call = reviewService.getReviewByUser(id);
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                List<Review> list = response.body();
                reviewAdapter.setList(list);
                binding.reviewRecycler.setAdapter(reviewAdapter);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });

        reviewAdapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Review review = reviewAdapter.getItem(position);
                Log.i(TAG, "onItemClick: " + review.toString());
                getProductDetail(review.getProduct_no());

            }
        });
    }

    private void getProductDetail(int productNo) {
        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<ProductDetail> call = productService.getDetailList(productNo);
        call.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                ProductDetail productDetail = response.body();
                Bundle args = new Bundle();
                args.putSerializable("product", productDetail);
                if(productDetail.getImages_no() != null) {
                    args.putIntegerArrayList("imageNoList", new ArrayList<>(productDetail.getImages_no()));
                }

                navController.navigate(R.id.dest_detail, args);
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {

            }
        });
    }

}