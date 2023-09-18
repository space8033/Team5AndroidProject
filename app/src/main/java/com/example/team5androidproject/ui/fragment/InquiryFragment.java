package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentInquiryBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.Inquiry;
import com.example.team5androidproject.service.MemberService;
import com.example.team5androidproject.service.ReviewService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.InquiryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquiryFragment extends Fragment {
    private static final String TAG = "InquiryFragment";
    private FragmentInquiryBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInquiryBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL,false
        );

        binding.inquriyRecycler.setLayoutManager(linearLayoutManager);
        InquiryAdapter inquiryAdapter = new InquiryAdapter();
        String id = AppKeyValueStore.getValue(getContext(), "userId");
        MemberService memberService = ServiceProvider.getMemberService(getContext());
        Log.i(TAG, "initRecyclerView: " + id);
        if(id == null || id.length() == 0) {
            Call<List<Inquiry>> call = memberService.allInquiry();
            call.enqueue(new Callback<List<Inquiry>>() {
                @Override
                public void onResponse(Call<List<Inquiry>> call, Response<List<Inquiry>> response) {
                    List<Inquiry> list = response.body();
                    inquiryAdapter.setList(list);
                    binding.inquriyRecycler.setAdapter(inquiryAdapter);
                }

                @Override
                public void onFailure(Call<List<Inquiry>> call, Throwable t) {

                }
            });
        }else {
            Call<List<Inquiry>> call = memberService.inquiry(id);
            call.enqueue(new Callback<List<Inquiry>>() {
                @Override
                public void onResponse(Call<List<Inquiry>> call, Response<List<Inquiry>> response) {
                    List<Inquiry> list = response.body();
                    inquiryAdapter.setList(list);
                    binding.inquriyRecycler.setAdapter(inquiryAdapter);
                }

                @Override
                public void onFailure(Call<List<Inquiry>> call, Throwable t) {

                }
            });
        }
    }
}