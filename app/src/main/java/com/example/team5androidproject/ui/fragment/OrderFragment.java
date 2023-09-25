package com.example.team5androidproject.ui.fragment;

import android.content.DialogInterface;
import android.icu.text.DecimalFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentOrderBinding;
import com.example.team5androidproject.datastore.AppKeyValueStore;
import com.example.team5androidproject.dto.Coupon;
import com.example.team5androidproject.dto.Login;
import com.example.team5androidproject.dto.Order;
import com.example.team5androidproject.dto.OrderUser;
import com.example.team5androidproject.dto.Receiver;
import com.example.team5androidproject.service.AddressService;
import com.example.team5androidproject.service.CartService;
import com.example.team5androidproject.service.CouponService;
import com.example.team5androidproject.service.MemberService;
import com.example.team5androidproject.service.OrderService;
import com.example.team5androidproject.service.ServiceProvider;
import com.example.team5androidproject.ui.adapter.OrderAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment {
    private static final String TAG = "OrderFragment";
    private FragmentOrderBinding binding;
    private NavController navController;
    private List<Integer> cart_no; // 전역변수로 선언
    private int firstCart;
    private OrderAdapter orderAdapter; // OrderAdapter 객체 선언
    private int totalPayProductPrice;
    private DecimalFormat df = new DecimalFormat("#,###");
    private CartService cartService;
    private CouponService couponService;
    private OrderService orderService;
    private MemberService memberService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        cart_no = bundle.getIntegerArrayList("cartIds");
        int firstCart = cart_no.get(0);
        Coupon coupon = (Coupon) getArguments().getSerializable("coupon");

        if (coupon != null) {
            int couponPrice = coupon.getCoupon_value();
            binding.couponPrice.setText("- " + df.format(couponPrice) + " 원");
            binding.lastCoupon.setText("- " + df.format(couponPrice) + " 원");
        }

        Receiver receiver = (Receiver) bundle.getSerializable("address");
        if (receiver != null) {
            binding.txtAddress.setText(receiver.getReceiverAddress() + " " + receiver.getReceiverZip());
        }
        String iName = (String) bundle.getString("name");
        String iPhone = (String) bundle.getString("phone");
        String iDetail = (String) bundle.getString("detail");
        String iRoad = (String) bundle.getString("road");
        String iJibun = (String) bundle.getString("jibun");
        String iExtra = (String) bundle.getString("extra");


        if (iName != null) {
            binding.inputName.setText(iName);
        }
        if (iPhone != null) {
            binding.inputPhone.setText(iPhone);
        }
        if (iDetail != null) {
            binding.inputDetail.setText(iDetail);
        }
        if(iRoad != null) {
            binding.txtAddressRoad.setText(iRoad);
        }
        if(iJibun != null) {
            binding.txtAddressJibun.setText(iJibun);
        }
        if(iExtra != null) {
            binding.txtAddressExtra.setText(iExtra);
        }

        initBtnAddress();
        initBtnMypage();
        initOrderUser(firstCart);
        initRecyclerView();
        intiClickUsePoint();
        intiPayCoupon();
        initBuyrderItem();


        return binding.getRoot();
    }

    private void initRecyclerView() {

        OrderService orderService = ServiceProvider.getOrderService(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.orderRecyclerView.setLayoutManager(linearLayoutManager);

        orderAdapter = new OrderAdapter(); // orderAdapter 초기화

        Call<List<Order>> call = orderService.getOrderInfos(cart_no);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orderList = response.body();
                orderAdapter.setList(orderList);
                binding.orderRecyclerView.setAdapter(orderAdapter);

                // TotalPayProductPrice 가져오기
                totalPayProductPrice = orderAdapter.getTotalPayProductPrice();
                String coupon1 = binding.couponPrice.getText().toString();
                String coupon2 = coupon1.replaceAll("[^0-9]", "");

                int couponPrice = Integer.parseInt(coupon2);
                int totalPayProductPrice = orderAdapter.getTotalPayProductPrice();
                binding.payPrice.setText(df.format(totalPayProductPrice) + " 원");
                int newTotalPrice = totalPayProductPrice - couponPrice;
                binding.lastPrice.setText(df.format(newTotalPrice + 2500) + " 원"); //총가격
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.i(TAG, "recyclerView 불러오기 실패");
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private void intiPayCoupon() {
        binding.useCouponBtn.setOnClickListener(c -> {
            Bundle bundle = getArguments();
            String name = binding.inputName.getText().toString();
            String phone = binding.inputPhone.getText().toString();
            String detail = binding.inputDetail.getText().toString();
            String road = binding.txtAddressRoad.getText().toString();
            String jibun = binding.txtAddressJibun.getText().toString();
            String extra = binding.txtAddressExtra.getText().toString();


            bundle.putString("name", name);
            bundle.putString("phone", phone);
            bundle.putString("detail", detail);
            bundle.putString("road", road);
            bundle.putString("jibun", jibun);
            bundle.putString("extra", extra);

            navController.navigate(R.id.dest_pay_coupon, bundle);
        });
        String coupon1 = binding.couponPrice.getText().toString();
        String coupon2 = coupon1.replaceAll("[^0-9]", "");

        int couponPrice = Integer.parseInt(coupon2);
        int newTotalPrice = totalPayProductPrice - couponPrice;

        binding.lastPrice.setText(df.format(newTotalPrice + 2500) + " 원"); //총가격
    }

    private void initBtnMypage() {
        binding.btnMypage.setOnClickListener(v -> {
            navController.navigate(R.id.dest_mypage);
        });
    }

    private void initOrderUser(int firstCart) {
        String userId = AppKeyValueStore.getValue(getContext(), "userId");
        OrderService orderService = ServiceProvider.getOrderService(getContext());
        Call<OrderUser> call = orderService.getOrderItems(firstCart);
        call.enqueue(new Callback<OrderUser>() {
            @Override
            public void onResponse(Call<OrderUser> call, Response<OrderUser> response) {
                OrderUser orderUser = response.body();
                binding.orderName.setText(String.valueOf(orderUser.getUsers_name()));
                binding.orderEmail.setText(String.valueOf(orderUser.getUsers_email()));
                binding.orderPhone.setText(String.valueOf(orderUser.getUsers_phone()));
                binding.userPoint.setText(df.format(orderUser.getPoint()) + " P");
            }

            @Override
            public void onFailure(Call<OrderUser> call, Throwable t) {
            }
        });

        binding.btnMypage.setOnClickListener(v -> {
            Bundle bundle = getArguments();
            List<Integer> cart_nos = bundle.getIntegerArrayList("cartIds");
            Log.i(TAG, "번들에서 넘어온 cart_no 객체들" + cart_nos);
            Coupon coupon = (Coupon) getArguments().getSerializable("coupon");
            int coupon_no = (coupon != null) ? coupon.getCoupon_no() : 0; // 쿠폰을 선택하지 않은 경우 0으로 초기화
            Log.i(TAG, "선택한 쿠폰의 번호" + coupon_no);

            cartService = ServiceProvider.getCartService(getContext());
            couponService = ServiceProvider.getCouponService(getContext());
            memberService = ServiceProvider.getMemberService(getContext());

            for (Integer cart_no : cart_nos) {
                // 각 cart_no에 대한 삭제 요청 보내기
                Call<Void> callDelete = cartService.deleteOneCart(cart_no);
                callDelete.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // 성공적으로 삭제되었을 때의 처리
                            Log.i(TAG, "카트 데이터 삭제 성공: " + cart_no);

                            // 사용한 쿠폰 삭제 요청
                            if (coupon_no != 0) {
                                Call<Void> callDelete = couponService.deleteCoupon(coupon_no);
                                callDelete.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Log.i(TAG, "쿠폰 삭제 성공: " + coupon_no);
                                    }
                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                    }
                                });
                            }
                            //적립금 차감 잔액(기존 적립금 - 사용한 적립금) 을 db에 업데이트
                            String enteredPointText = binding.editTextPoint.getText().toString();
                            String userPoint = binding.userPoint.getText().toString();
                            String numericPart = userPoint.replaceAll("[^0-9]", "");


                            // 빈 문자열("")이면 0으로 처리
                            int enteredPointValue = enteredPointText.isEmpty() ? 0 : Integer.parseInt(enteredPointText);
                            int UserPoint = userPoint.isEmpty() ? 0 : Integer.parseInt(numericPart);

                            Log.i(TAG, "사용한 포인트" + enteredPointValue);

                            int balance_point = UserPoint - enteredPointValue;

                            Log.i(TAG, "남은 잔액 포인트"+ balance_point);
                            Log.i(TAG, "onResponse: " + userId);
                            Call<Void> updatePoint = memberService.updatePoint(userId, balance_point);
                            updatePoint.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Log.i(TAG, "포인트 업데이트 완료");
                                    Log.i(TAG, "유저의 아이디" + userId);
                                    Log.i(TAG, "유저의 포인트" + balance_point);
                                    navController.navigate(R.id.dest_mypage);
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });

                        } else {
                            // 삭제 실패 시의 처리
                            Log.e(TAG, "카트 데이터 삭제 실패: " + cart_no);
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // 네트워크 오류 등으로 실패했을 때의 처리
                        Log.e(TAG, "카트 데이터 삭제 오류: " + cart_no, t);
                    }
                });
            }
            AddressService addressService = ServiceProvider.getAddressService(getContext());

            MultipartBody.Part address_receiver = MultipartBody.Part.createFormData("address_receiver", binding.inputName.getText().toString());
            MultipartBody.Part address_roadAddress = MultipartBody.Part.createFormData("address_roadAddress", binding.txtAddressRoad.getText().toString());
            MultipartBody.Part address_jibunAddress = MultipartBody.Part.createFormData("address_jibunAddress", binding.txtAddressJibun.getText().toString());
            MultipartBody.Part address_extraAddress = MultipartBody.Part.createFormData("address_extraAddress", binding.txtAddressExtra.getText().toString());
            MultipartBody.Part address_detail = MultipartBody.Part.createFormData("address_detail", binding.inputDetail.getText().toString());
            MultipartBody.Part users_users_id = MultipartBody.Part.createFormData("users_users_id", userId);
            MultipartBody.Part users_phone = MultipartBody.Part.createFormData("users_phone", binding.inputPhone.getText().toString());

            Call<Void> addressCall = addressService.addressRegister(
                    address_receiver, address_roadAddress, address_jibunAddress, address_extraAddress, address_detail, users_users_id, users_phone);
            addressCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });



        });


    }

    private void intiClickUsePoint() {
        binding.usePointBtn.setOnClickListener(v -> {
            String userPointText = binding.userPoint.getText().toString();
            String numericPart = userPointText.replaceAll("[^0-9]", "");
            int maxPointToUse = Integer.parseInt(numericPart);
            String enteredPoint = binding.editTextPoint.getText().toString();

            try {
                int enteredPointValue = Integer.parseInt(enteredPoint);

                if (enteredPointValue > maxPointToUse) {
                    // 사용하려는 적립금이 보유 적립금을 초과하는 경우
                    showDialog("적립금 초과", "입력한 적립금이 보유 적립금을 초과합니다.");
                } else if (enteredPointValue < 0) {
                    showDialog("잘못된 금액", "0 이상의 값을 입력하세요.");
                } else {
                    // 적립금 사용 가능한 범위 내의 값을 입력한 경우
                    int totalPayProductPrice = orderAdapter.getTotalPayProductPrice();
                    String coupon1 = binding.couponPrice.getText().toString();
                    String coupon2 = coupon1.replaceAll("[^0-9]", "");

                    int couponPrice = Integer.parseInt(coupon2);
                    int newTotalPrice = totalPayProductPrice - enteredPointValue - couponPrice;

                    binding.lastPoint.setText(("- " + df.format(enteredPointValue) + " 원")); //최종 적립금 가격
                    binding.lastPrice.setText(df.format(newTotalPrice + 2500) + " 원"); //총가격
                }
            } catch (NumberFormatException e) {
                // 유효하지 않은 숫자가 입력된 경우
                showDialog("유효하지 않은 값", "숫자 값을 입력하세요.");
            }
        });
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.editTextPoint.setText("0");
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void initBtnAddress() {
        binding.btnAddressAdd.setOnClickListener(v -> {
            Bundle bundle = getArguments();
            String name = binding.inputName.getText().toString();
            String phone = binding.inputPhone.getText().toString();
            String detail = binding.inputDetail.getText().toString();
            String road = binding.txtAddressRoad.getText().toString();
            String jibun = binding.txtAddressJibun.getText().toString();
            String extra = binding.txtAddressExtra.getText().toString();

            bundle.putString("name", name);
            bundle.putString("phone", phone);
            bundle.putString("detail", detail);
            bundle.putString("road", road);
            bundle.putString("jibun", jibun);
            bundle.putString("extra", extra);

            navController.navigate(R.id.dest_add_address, bundle);
        });
    }

    private void initBuyrderItem() {

    }
}