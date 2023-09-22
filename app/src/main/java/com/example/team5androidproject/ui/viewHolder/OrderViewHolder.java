package com.example.team5androidproject.ui.viewHolder;

import android.icu.text.DecimalFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Order;
import com.example.team5androidproject.service.OrderService;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView recyclerView;
    private NavController navController;
    private static final String TAG = "OrderViewHolder";
    private ImageView product_imgFile;
    private TextView pay_product_name;
    private TextView pay_product_qty;
    private TextView pay_product_option;
    private TextView pay_product_price;





    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.order_recyclerView);
        product_imgFile = (ImageView) itemView.findViewById(R.id.pay_imgFile);

        pay_product_name = (TextView) itemView.findViewById(R.id.pay_prodcut_name);
        pay_product_qty = (TextView)  itemView.findViewById(R.id.pay_product_qty);
        pay_product_option = (TextView) itemView.findViewById(R.id.pay_prodcut_option);
        pay_product_price = (TextView) itemView.findViewById(R.id.pay_product_price);
    }

    public void setData(Order order){
        OrderService.loadImage(order.getProduct_no(), product_imgFile);
        Log.i(TAG, "viewHoler에서 order: " + order.toString());
        pay_product_name.setText(order.getProduct_name());
        pay_product_qty.setText(String.valueOf(order.getCart_qty())+ " 개");
        pay_product_option.setText(order.getProductOption_type());
        DecimalFormat df =new DecimalFormat("#,###");
        pay_product_price.setText(String.valueOf(df.format(order.getProduct_price()*order.getCart_qty())+" 원"));
    }
}
