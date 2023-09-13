package com.example.team5androidproject.ui.viewHolder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Cart;
import com.example.team5androidproject.service.CartService;
import com.example.team5androidproject.ui.adapter.CartAdapter;

import org.w3c.dom.Text;

public class CartViewHolder extends RecyclerView.ViewHolder {

    private int cart_no;
    private ImageView cart_ImageView;
    private TextView cart_name;
    private TextView cart_price;
    private TextView cart_qty;
    private TextView cart_option;



    private static final String TAG = "CartViewHolder";

    public CartViewHolder(@NonNull View itemView, CartAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        cart_name = (TextView) itemView.findViewById(R.id.cart_name);
        cart_ImageView = (ImageView) itemView.findViewById(R.id.cart_imgFile);
        cart_price = (TextView) itemView.findViewById(R.id.cart_price);
        cart_ImageView = (ImageView) itemView.findViewById(R.id.cart_imgFile);
        cart_qty = (TextView) itemView.findViewById(R.id.cart_qty);

        itemView.setOnClickListener(v->{
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });
    }
    public void setData(Cart cart) {
        cart_no = cart.getCart_no();
        CartService.loadImage(cart.getProduct_no(), cart_ImageView);
        cart_qty.setText(cart.getCart_qty());
/*
        cart_option.setText(cart.getCart_());
*/
        cart_price.setText(cart.getProduct_price());
        cart_name.setText(cart.getProduct_name());

    }
}
