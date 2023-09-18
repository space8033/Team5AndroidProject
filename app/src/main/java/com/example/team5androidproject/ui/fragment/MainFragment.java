package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.dto.ProductDetail;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.AdPagerAdapter;
import com.example.team5androidproject.databinding.FragmentMainBinding;
import com.example.team5androidproject.ui.adapter.DetailThumbnailAdapter;
import com.example.team5androidproject.ui.adapter.ProductAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private FragmentMainBinding binding;
    private NavController navController;
    private Handler handler = new Handler();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initMenu();
        initPagerView();
        initMainRecyclerView();
        hideButton();
        initFloatButton();

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

    private void initPagerView() {
        AdPagerAdapter adPagerAdapter = new AdPagerAdapter(this);
        binding.viewPager.setAdapter(adPagerAdapter);

        //탭 레이아웃 기능
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            }
        });
        tabLayoutMediator.attach();

        //자동 넘김 기능
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = binding.viewPager.getCurrentItem();
                int totalItems = binding.viewPager.getAdapter() != null ? binding.viewPager.getAdapter().getItemCount() : 0;
                int nextItem = (currentItem + 1) % totalItems;

                binding.viewPager.setCurrentItem(nextItem, true);

                handler.postDelayed(this, 2500);
            }
        };

        handler.postDelayed(runnable, 2500);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    private void initMainRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        binding.recyclerViewMain.setLayoutManager(layoutManager);


        ProductAdapter listAdapter =new ProductAdapter();
        ProductService listService = ServiceProvider.getProductService(getContext());
        Call<List<Product>> call = listService.getProductList();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                listAdapter.setList(list);
                binding.recyclerViewMain.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        listAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Product product = listAdapter.getItem(position);
                ProductDetail productDetail = convertToProductDetail(product);

                Bundle args = new Bundle();
                args.putSerializable("product",productDetail);
                if (product.getImage_no() != null) {
                    args.putIntegerArrayList("imageNoList", new ArrayList<>(product.getImage_no()));
                }
                navController.navigate(R.id.action_dest_main_to_dest_detail,args);
            }
        });

    }
    private ProductDetail convertToProductDetail(Product product) {
        ProductDetail productDetail = new ProductDetail();

        productDetail.setProduct_no(product.getProduct_no());
        productDetail.setProduct_name(product.getProduct_name());
        productDetail.setProduct_price(product.getProduct_price());
        productDetail.setProductoption_type(product.getProduct_option());
        productDetail.setImages_no(product.getImage_no());

        return productDetail;
    }
    private void initFloatButton() {
        binding.nestedMainScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > oldScrollY) {
                    showButton();
                }else {
                    hideButton();
                }
            }
        });

        binding.btnFloatMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.nestedMainScroll.smoothScrollTo(0, 0);
            }
        });
    }

    private void hideButton() {
        binding.btnFloatMain.hide();
    }

    private void showButton() {
        binding.btnFloatMain.show();
    }
}