package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentCartBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.Cart;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.dto.Review;
import com.example.team5androidproject.service.CartService;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.service.ReviewService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.CartAdapter;
import com.example.team5androidproject.ui.adapter.ProductAdapter;
import com.example.team5androidproject.ui.adapter.ReviewAdapter;
import com.example.team5androidproject.ui.viewHolder.CartViewHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment implements CartAdapter.CartAdapterListener {
    private static final String TAG = "CartFragment";
    private FragmentCartBinding binding;
    private NavController navController;
    private CartAdapter cartAdapter;
    private List<Integer> checkedCartIds = new ArrayList<>(); // 선택한 카트 아이디 목록을 저장할 리스트



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        cartAdapter = new CartAdapter();
        // CartAdapter에 리스너 설정
        cartAdapter.setCartAdapterListener(this);

        initBtnOrder();
        initMenu();
        initRecyclerView();

        return binding.getRoot();
    }

    private void initMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.cart_fragment,menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.dest_main){
                    navController.popBackStack(R.id.dest_main,false);
                    return true;
                }
                return false;
            }
        };
        getActivity().addMenuProvider(menuProvider,getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    public void onPause() {
        super.onPause();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }


    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v -> {
            List<Integer> selectedCartIds = cartAdapter.getCheckedCartIds();
            Log.i(TAG, "cartAdapter 결과값" + cartAdapter.getCheckedCartIds());
            Log.i(TAG, "선택된 카트의 아이디들" + selectedCartIds);

            onCartIdsSelected(selectedCartIds);

            navController.navigate(R.id.action_dest_cart_to_dest_order);
        });
    }

    @Override
    public void onCartIdsSelected(List<Integer> checkedCartIds) {
        // 선택한 카트 아이디 목록 업데이트
        Log.i(TAG, "Selected Cart IDs: " + checkedCartIds);
    }

    private void initRecyclerView() {
        String userId = AppKeyValueStore.getValue(getContext(), "userId");
        CartService cartService = ServiceProvider.getCartService(getContext());

        //카트 수 받아오기
        Call<Integer> callCount = cartService.getCartCount(userId);

        callCount.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int itemCount = response.body();
                String countText = itemCount + "개의 상품이 담겨있습니다";
                binding.countCart.setText(countText);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

        //장바구니 리사이클러뷰 가져오기
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL,false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        CartAdapter cartAdapter =new CartAdapter();
        cartAdapter.getAllCheckBox(binding.allSelect);
        cartAdapter.getSelectNumTextView(binding.selectNum);
        cartAdapter.getSelectPriceTextView(binding.selectPrice);
        cartAdapter.getCountCartTextview(binding.countCart);
        cartAdapter.getDeleteAllBtn(binding.deleteAll);

        Call<List<Cart>> call = cartService.getCartList(userId);

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> list = response.body();
                cartAdapter.setList(list);
                binding.recyclerView.setAdapter(cartAdapter);
                Log.i(TAG, "카트테스트");
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}