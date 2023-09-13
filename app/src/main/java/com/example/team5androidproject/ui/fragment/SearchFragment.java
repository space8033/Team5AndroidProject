package com.example.team5androidproject.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentSearchBinding;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private FragmentSearchBinding binding;
    private NavController navController;
    private EditText editText;
    private ImageButton searchButton;
    private ProductAdapter productAdapter;
    private String keyword = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        binding = FragmentSearchBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);
        productAdapter = new ProductAdapter();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        editText = rootView.findViewById(R.id.editText);
        searchButton = rootView.findViewById(R.id.searchButton);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    keyword = v.getText().toString();
                    performSearch();
                    Log.i(TAG, "onEditorAction 실행");
                    return true;
                }
                Log.i(TAG, "onEditorAction 실패");
                return false;
            }
        });
        initBtnBack();
        initBtnMain();

        return binding.getRoot();
    }

    private void performSearch() {
        // Retrofit API 호출을 사용하여 키워드를 기반으로 제품 검색을 수행합니다.
        ProductService productService = ServiceProvider.getListService(requireContext());

        //Call<List<Product>> call = productService.searchProducts(keyword);

        /*call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> searchResults = response.body();

                    // 검색어가 포함된 상품만 필터링
                    List<Product> filteredResults = filterResultsByKeyword(searchResults, keyword);

                    // RecyclerView 어댑터를 검색 결과로 업데이트합니다
                    listAdapter.setList(filteredResults);
                    // 어댑터에 데이터가 변경되었음을 알립니다
                    listAdapter.notifyDataSetChanged();
                    navController.navigate(R.id.action_dest_search_to_dest_list);
                    // ListFragment로 이동
                    Log.i(TAG, "onResponse 성공");
                } else {
                    Log.i(TAG, "onResponse 실패");

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i(TAG, "onFailure 실행");
                // 네트워크 오류 처리
            }
        });*/
    }

    private List<Product> filterResultsByKeyword(List<Product> results, String keyword) {
        List<Product> filteredResults = new ArrayList<>();
        for (Product product : results) {
            if (product.getProduct_name().contains(keyword)) {
                filteredResults.add(product);
            }
        }
        return filteredResults;
    }

    private void initBtnMain() {
        binding.Home.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_search_to_dest_list);
            //navController.popBackStack(R.id.dest_main, false);
        });
    }

    private void initBtnBack() {
        binding.Back.setOnClickListener(v->{
            navController.popBackStack();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.show();
    }
}