package com.example.team5androidproject.ui.fragment;


import android.os.Bundle;
import android.text.TextUtils;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
        binding = FragmentSearchBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);
        productAdapter = new ProductAdapter();

        editText = binding.editText;
        searchButton = binding.searchButton;

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initSearch();
        initBtnBack();
        initBtnMain();

        return binding.getRoot();
    }

    private void initSearch() {
        searchButton.setOnClickListener(v -> {
            Log.i(TAG, "검색버튼 클릭");
            performSearch();
        });

        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });
    }

    private void performSearch() {
        keyword = editText.getText().toString();
        Log.i(TAG, "keyword : " +keyword);
        if (!TextUtils.isEmpty(keyword)) {
            // 검색어를 사용하여 서버로 검색 요청을 보냅니다.
            ProductService productService = ServiceProvider.getProductService(getContext());
            Call<List<Product>> call = productService.searchProducts(keyword);

            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
                        List<Product> searchResults = response.body();
                        Log.i(TAG, "searchResults : " + searchResults);
                        // 검색 결과를 RecyclerView에 표시합니다.
                        productAdapter.setList(searchResults);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("searchResults", new ArrayList<>(searchResults));
                        getParentFragmentManager().setFragmentResult("searchResultsKey", bundle);

                        bundle.putString("keyword",keyword);
                        // ListFragment로 이동
                        navController.navigate(R.id.action_dest_search_to_dest_list,bundle);
                    } else {
                        // 검색 실패 처리
                        Log.e(TAG, "검색 실패: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
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