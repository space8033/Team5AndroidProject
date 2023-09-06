package com.example.team5androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.team5androidproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        initAppBar();
    }

    private void initAppBar() {
        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        NavController navController = navHostFragment.getNavController();

        //그래프상에서 최상위 수준 대상을 지정(홈 아이콘으로 표시된 대상)
        AppBarConfiguration appBarConfiguration =  new AppBarConfiguration
                .Builder(navController.getGraph())
                .build();

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
    }
}