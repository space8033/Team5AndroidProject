package com.example.team5androidproject;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.team5androidproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NavController navController;
    private ActivityMainBinding binding;
    private boolean down = false;
    private Float height = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //상태바 투명하게 설정
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        initAppBar();
        setContentView(binding.getRoot());
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