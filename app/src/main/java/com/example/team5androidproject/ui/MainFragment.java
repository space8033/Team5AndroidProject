package com.example.team5androidproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private FragmentMainBinding binding;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);

        initBtnDetail();
        initMenu();

        return binding.getRoot();

    }

    private void initMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.main_fragment,menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.dest_search){
                    navController.navigate(R.id.action_dest_main_to_dest_search);
                    return true;
                } else if (menuItem.getItemId() == R.id.dest_cart){
                    navController.navigate(R.id.action_dest_main_to_dest_cart);
                    return true;
                }
                return false;
            }
        };
        getActivity().addMenuProvider(menuProvider,getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void initBtnDetail() {
        binding.btnDetail.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_main_to_dest_detail);
        });
    }

}