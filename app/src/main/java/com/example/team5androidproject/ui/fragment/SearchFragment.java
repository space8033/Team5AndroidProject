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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentSearchBinding;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.ProductAdapter;
import com.example.team5androidproject.ui.adapter.RecentSearchAdapter;
import com.example.team5androidproject.ui.viewHolder.SearchHistoryManager;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
    private RecyclerView recentSearchRecyclerView;
    private List<String> recentSearchList;
    private RecentSearchAdapter recentSearchAdapter;
    private SearchHistoryManager searchHistoryManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recentSearchList = new ArrayList<>();
        searchHistoryManager = new SearchHistoryManager(requireContext());
        recentSearchList.addAll(searchHistoryManager.getSearchHistory());
        recentSearchAdapter = new RecentSearchAdapter(recentSearchList, new RecentSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String searchKeyword) {
                editText.setText(searchKeyword);
                performSearch();
            }

            @Override
            public void onDeleteClick(int position) {
                recentSearchList.remove(position);
                recentSearchAdapter.notifyItemRemoved(position);
                recentSearchAdapter.notifyItemRangeChanged(position, recentSearchList.size());

                //업데이트
                searchHistoryManager.saveSearchHistory(recentSearchList);
            }
        });
    }

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

        recentSearchRecyclerView = binding.getRoot().findViewById(R.id.recent_search);
        recentSearchRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        recentSearchRecyclerView.setAdapter(recentSearchAdapter);



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

                        updateSearchHistory(keyword);
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
            navController.popBackStack(R.id.dest_main, false);
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

    private void updateSearchHistory(String keyword) {
        // 기존 검색 기록 불러오기
        List<String> searchHistory = searchHistoryManager.getSearchHistory();

        // 새로운 검색어를 기록에 추가
        searchHistory.add(0,keyword);

        // 중복 제거를 위해 Set으로 변환 후 다시 List로 변환
        List<String> deduplicatedSearchHistory = new ArrayList<>(new LinkedHashSet<>(searchHistory));

        // 검색 기록 저장
        searchHistoryManager.saveSearchHistory(deduplicatedSearchHistory);
        recentSearchList.clear();
        recentSearchList.addAll(deduplicatedSearchHistory);
        recentSearchAdapter.notifyDataSetChanged();
    }
}