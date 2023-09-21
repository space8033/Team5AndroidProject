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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentListBinding;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.dto.ProductDetail;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    private FragmentListBinding binding;
    private NavController navController;
    private ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);


        initMenu();
        initRecyclerView();

        return binding.getRoot();
    }

    private void initMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.list_fragment,menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.dest_search){
                    navController.navigate(R.id.action_dest_list_to_dest_search);
                    return true;
                } else if (menuItem.getItemId() == R.id.dest_main){
                    navController.popBackStack(R.id.dest_main,false);
                    return true;
                }
                return false;
            }
        };
        getActivity().addMenuProvider(menuProvider,getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        binding.recyclerView.setLayoutManager(layoutManager);

        productAdapter = new ProductAdapter();
        String keyword = getArguments().getString("keyword", "");

        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Product>> call = productService.searchProducts(keyword);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                if(list.isEmpty()){
                    binding.tvNoReviews.setVisibility(View.VISIBLE);
                } else{
                    productAdapter.setList(list);
                    binding.recyclerView.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Product product = productAdapter.getItem(position);
                ProductDetail productDetail = convertToProductDetail(product);

                Bundle args = new Bundle();
                args.putSerializable("product",productDetail);
                if (product.getImage_no() != null) {
                    args.putIntegerArrayList("imageNoList", new ArrayList<>(product.getImage_no()));
                }
                navController.navigate(R.id.action_dest_list_to_dest_detail,args);
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
}