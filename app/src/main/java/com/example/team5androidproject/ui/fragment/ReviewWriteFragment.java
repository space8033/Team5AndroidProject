package com.example.team5androidproject.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.service.ReviewService;
import com.example.team5androidproject.service.ServiceProvider;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        String userId = AppKeyValueStore.getValue(getContext(), "userId");
        Bundle bundle = getArguments();
        int pno = bundle.getInt("pno");

        binding.btnWrite.setOnClickListener(v -> {
            ReviewService reviewService = ServiceProvider.getReviewService(getContext());

            MultipartBody.Part reviewTitle = MultipartBody.Part.createFormData("reviewTitle", binding.btitle.getText().toString());
            MultipartBody.Part reviewContents = MultipartBody.Part.createFormData("reviewContents", binding.bcontent.getText().toString());
            MultipartBody.Part reviewWriter = MultipartBody.Part.createFormData("reviewWriter", userId);
            MultipartBody.Part productNo = MultipartBody.Part.createFormData("productNo", String.valueOf(pno));
            MultipartBody.Part reviewRating = MultipartBody.Part.createFormData("reviewRating", String.valueOf((int)binding.reviewRating.getRating()));

            BitmapDrawable bitmapDrawable = (BitmapDrawable)binding.battach.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            String fileName = new Date().getTime() + ".jpg";
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), bytes);
            MultipartBody.Part file = MultipartBody.Part.createFormData("file", fileName, requestBody);

            Call<Void> call = reviewService.writeReview(reviewTitle, reviewContents, reviewWriter, productNo, reviewRating, file);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Snackbar snackbar = Snackbar.make(getContext(), binding.btnWrite, "리뷰 작성 완료", Snackbar.LENGTH_LONG);
                    snackbar.show();

                    navController.navigate(R.id.dest_mypage);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Snackbar snackbar = Snackbar.make(getContext(), binding.btnWrite, "리뷰 작성 실패", Snackbar.LENGTH_LONG);
                    snackbar.show();

                }
            });
        });
    }
}
