package com.example.team5androidproject;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.team5androidproject.databinding.ActivityMainBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //상태바 투명하게 설정
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        navController = navHostFragment.getNavController();

        binding.bottomNavigationView.getMenu().findItem(R.id.dest_mypage).setVisible(false);
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

    @Override
    protected void onDestroy() {
        AppKeyValueStore.remove(getApplicationContext(), "userId");
        Log.i(TAG, "onDestroy: 됨?");
        AppKeyValueStore.remove(getApplicationContext(), "password");
        super.onDestroy();
    }
}