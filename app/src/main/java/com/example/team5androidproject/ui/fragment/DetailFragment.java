package com.example.team5androidproject.ui.fragment;

import android.os.Bundle;
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

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentDetailBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;


public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;
    private NavController navController;
    private AutoCompleteTextView productStock;
    private ArrayAdapter<String> stockAdapter;
    private TextView selectedOptionText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(this);
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);


        initBtnOrder();
        initBtnCart();

        initOptionSelect();
        initStockSelect();
        initMenu();

        return binding.getRoot();
    }

    private void initStockSelect() {

        productStock = binding.productStock;
        selectedOptionText = binding.selectedOptionText2;

        String[] stockOptions = {"1", "2", "3", "4", "5", "6", "7", "8","9","10"};
        stockAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, stockOptions);
        stockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productStock.setAdapter(stockAdapter);

        productStock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStock = stockAdapter.getItem(position);
                if (selectedOptionText != null) {

                    selectedOptionText.setText(selectedStock);
                }
            }
        });
    }

    private void initOptionSelect() {
        productStock = binding.productOption;
        selectedOptionText = binding.selectedOptionText1;

        String[] stockOptions = {"Option 1", "Option 2", "Option 3", "Option 4"};
        stockAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, stockOptions);
        stockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productStock.setAdapter(stockAdapter);

        productStock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStock = stockAdapter.getItem(position);
                if (selectedOptionText != null) {

                    selectedOptionText.setText(selectedStock);
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