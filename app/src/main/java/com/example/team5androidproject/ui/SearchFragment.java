package com.example.team5androidproject.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentSearchBinding;
import com.google.android.material.appbar.AppBarLayout;


public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private FragmentSearchBinding binding;
    private NavController navController;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        binding = FragmentSearchBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // 추가: SearchView 초기화 및 검색 이벤트 처리 리스너 설정
        searchView = rootView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색 버튼을 눌렀을 때 처리
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경될 때 처리
                return true;
            }
        });

        initBtnBack();
        initBtnMain();

        return binding.getRoot();
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