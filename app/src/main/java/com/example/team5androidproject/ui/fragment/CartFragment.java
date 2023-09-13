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

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentCartBinding;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {
    private static final String TAG = "CartFragment";
    private FragmentCartBinding binding;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        initBtnOrder();
        initMenu();
        initRecyclerView();
        /*initCheckAll();*/

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

    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_cart_to_dest_order);
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL,false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        CartAdapter cartAdapter =new CartAdapter();
        CartService cartService = ServiceProvider.getCartService(getContext());
        Call<List<Cart>> call = cartService.getCartList();
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

/*    public class CartViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;

        public CartViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_item);
        }
    }

    private void initCheckAll() {
        // CartAdapter 인스턴스 생성
        CartAdapter cartAdapter = new CartAdapter();

        // RecyclerView에 Adapter 설정
        binding.recyclerView.setAdapter(cartAdapter);

        // 데이터를 설정하고 나서 항목 수 가져오기
        CartService cartService = ServiceProvider.getCartService(getContext());
        Call<List<Cart>> call = cartService.getCartList();
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> list = response.body();
                cartAdapter.setList(list);
                int cnt = cartAdapter.getItemCount();
                Log.i(TAG, "전체 아이템의 갯수: " + cnt);

                // RecyclerView를 업데이트하기 위해 notifyDataSetChanged 호출
                cartAdapter.notifyDataSetChanged();

                for (int i = 0; i < cnt; i++) {
                    // 각 ViewHolder에서 CheckBox를 가져와서 선택 상태로 설정
                    CartViewHolder cartViewHolder = (CartViewHolder) binding.recyclerView.findViewHolderForAdapterPosition(i);
                    if (cartViewHolder != null) {
                        cartViewHolder.checkBox.setChecked(true);
                        Log.i(TAG, "장바구니 항목 번호: " + i );
                    } else {
                        Log.i(TAG, "ViewHolder가 아직 생성되지 않았습니다.");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }*/
}