package com.example.team5androidproject.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.MainActivity;
import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.ActivityMainBinding;
import com.example.team5androidproject.databinding.FragmentLoginBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.Login;
import com.example.team5androidproject.service.MemberService;
import com.example.team5androidproject.service.ServiceProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";
    private FragmentLoginBinding binding;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        requireActivity().findViewById(R.id.bottom_navigation_view).setVisibility(View.GONE);

        initBtnLogin();
        initBtnCancel();

        return binding.getRoot();
    }

    private void initBtnLogin() {
        binding.btnLogin.setOnClickListener(v -> {
            String userId = binding.mid.getText().toString();
            String password = binding.mpassword.getText().toString();
            Log.i(TAG, "initBtnLogin: " + userId);

            MemberService memberService = ServiceProvider.getMemberService(getContext());
            Call<Login> call = memberService.login(userId, password);
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Login login = response.body();
                    if(login.getResult().equals("success")) {
                        AppKeyValueStore.put(getContext(), "userId", login.getUserId());
                        AppKeyValueStore.put(getContext(), "password", login.getPassword());

                        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
                        bottomNavigationView.getMenu().findItem(R.id.dest_login).setVisible(false);
                        bottomNavigationView.getMenu().findItem(R.id.dest_mypage).setVisible(true);

                        navController.popBackStack();
                    }else {
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("로그인 실패")
                                .setMessage(login.getResult())
                                .setPositiveButton("확인",null)
                                .create();
                        alertDialog.show();
                    }

                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {

                }
            });
        });
    }

    private void initBtnCancel() {
        binding.btnCancel.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().findViewById(R.id.bottom_navigation_view).setVisibility(View.VISIBLE);
    }
}