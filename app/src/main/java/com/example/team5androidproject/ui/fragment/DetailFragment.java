package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentDetailBinding;
import com.example.team5androidproject.dto.ProductDetail;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.DetailPagerAdapter;
import com.example.team5androidproject.ui.adapter.DetailThumbnailAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;
    private NavController navController;
    private AutoCompleteTextView productStock;
    private AutoCompleteTextView productOption;
    private ArrayAdapter<String> stockAdapter1;
    private ArrayAdapter<String> stockAdapter2;
    private TextView selectedOptionText1;
    private TextView selectedOptionText2;
    private ViewPager2 viewPagerDetailThumbnail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(this);
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        initDetailPagerView();
        initBtnOrder();
        initBtnCart();

        initStockSelect();
        initMenu();

        Bundle bundle= getArguments();
        ProductDetail productDetail =(ProductDetail) bundle.getSerializable("product");
        Log.i(TAG, productDetail.toString());
        initContent(productDetail);

        viewPagerDetailThumbnail = binding.getRoot().findViewById(R.id.view_pager);
        initDetailThumbnail();

        return binding.getRoot();
    }

    private void initDetailThumbnail() {
        Bundle args = getArguments();
        if (args != null) {
            ProductDetail productDetail = (ProductDetail) args.getSerializable("product");

            // ProductDetail 객체가 null이 아니고 Images_no가 있는 경우에만 처리
            if (productDetail != null && productDetail.getImages_no() != null) {
                List<Integer> imageNoList = productDetail.getImages_no();

                // DetailThumbnailAdapter를 설정하고 ViewPager2에 연결
                DetailThumbnailAdapter adapter = new DetailThumbnailAdapter(imageNoList);
                viewPagerDetailThumbnail.setAdapter(adapter);
            }
        }
    }

    private void initContent(ProductDetail productDetail){
        int product_no = productDetail.getProduct_no();
        Log.i(TAG, "Product_name : " + productDetail.getProduct_name());
        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<ProductDetail> call = productService.getDetailList(product_no);
        Log.i(TAG, "Call : " + call);
        call.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                ProductDetail dbdetail =response.body();
                Log.i(TAG, "dbdetail : " + dbdetail.toString());
                binding.txtProductName.setText(dbdetail.getProduct_name());
                binding.txtProductPrice.setText(String.valueOf(dbdetail.getProduct_price()));

                productOption = binding.productOption;
                selectedOptionText1 = binding.selectedOptionText1;
                Log.i(TAG, "옵션 : " + dbdetail.getProductoption_type());
                String[] typeOptions = dbdetail.getProductoption_type().toArray(new String[0]);
                stockAdapter1 = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, typeOptions);
                stockAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                productOption.setAdapter(stockAdapter1);
                productOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedStock = stockAdapter1.getItem(position);
                        if (selectedOptionText1 != null) {

                            selectedOptionText1.setText(selectedStock);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void initDetailPagerView() {
        DetailPagerAdapter detailPagerAdapter = new DetailPagerAdapter(this);
        binding.viewpagerDetail.setAdapter(detailPagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.viewpagerDetail, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0) {
                    tab.setText("상품 상세");
                }else if(position == 1) {
                    tab.setText("주문 후기");
                }
            }
        });
        tabLayoutMediator.attach();
    }

    private void initStockSelect() {

        productStock = binding.productStock;
        selectedOptionText2 = binding.selectedOptionText2;

        String[] stockOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        stockAdapter2 = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, stockOptions);
        stockAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productStock.setAdapter(stockAdapter2);

        productStock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStock = stockAdapter2.getItem(position);
                if (selectedOptionText2 != null) {

                    selectedOptionText2.setText(selectedStock);
                }
            }
        });
    }

    private void initMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.detail_fragment,menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.dest_search){
                    navController.navigate(R.id.action_dest_detail_to_dest_search);
                    return true;
                }
                return false;
            }
        };
        getActivity().addMenuProvider(menuProvider,getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }
    @Override
    public void onPause() {
        super.onPause();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }


    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_detail_to_dest_cart);
        });
    }

    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_detail_to_dest_order);
        });
    }
}