package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentReviewWriteBinding;
import com.google.android.material.snackbar.Snackbar;

public class ReviewWriteFragment extends Fragment {
    private static final String TAG = "ReviewWriteFragment";
    private FragmentReviewWriteBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReviewWriteBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initBtnSelectImage();
        initBtnWriteReview();

        return binding.getRoot();
    }

    private void initBtnSelectImage() {
        ActivityResultLauncher<PickVisualMediaRequest> activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.PickVisualMedia(),
                        uri -> {
                            if (uri != null) {
                                binding.battach.setImageURI(uri);
                                binding.battach.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }
                        }
                );
        binding.btnImageSelect.setOnClickListener(v->{
            PickVisualMediaRequest request = new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build();
            activityResultLauncher.launch(request);
        });
    }

    private void initBtnWriteReview() {
        binding.btnWrite.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(this.getContext(), binding.btnWrite, "리뷰 작성 완료", Snackbar.LENGTH_LONG);
            snackbar.show();

            navController.navigate(R.id.dest_mypage);
        });
    }
}
