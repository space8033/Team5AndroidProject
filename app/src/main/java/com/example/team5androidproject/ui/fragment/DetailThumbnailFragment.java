package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.ProductDetail;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.ui.adapter.DetailThumbnailAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailThumbnailFragment extends Fragment {
    private ViewPager2 viewPagerDetailImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_thumbnail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerDetailImg = view.findViewById(R.id.view_pager);

        Bundle args = getArguments();
        if (args != null) {
            ProductDetail productDetail = (ProductDetail) args.getSerializable("product");

            // ProductDetail 객체가 null이 아니고 Images_no가 있는 경우에만 처리
            if (productDetail != null && productDetail.getImages_no() != null) {
                List<Integer> imageNoList = productDetail.getImages_no();

                // DetailThumbnailAdapter를 설정하고 ViewPager2에 연결
                DetailThumbnailAdapter adapter = new DetailThumbnailAdapter(imageNoList);
                viewPagerDetailImg.setAdapter(adapter);
            }
        }
    }
}