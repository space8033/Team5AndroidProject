package com.example.team5androidproject.ui.viewHolder;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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

    private int product_no;
    private int cart_no;
    private TextView cart_qty;
    private TextView product_name;
    private TextView product_price;
    private ImageView product_imgFile;
    private TextView productOption_type;
    private TextView cartQtyTextView;
    public ImageButton qtyPlusButton;
    public ImageButton qtyMinusButton;


    private static final String TAG = "CartViewHolder";

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        product_name = (TextView) itemView.findViewById(R.id.cart_name);
        product_imgFile = (ImageView) itemView.findViewById(R.id.cart_imgFile);
        product_price = (TextView) itemView.findViewById(R.id.cart_price);
        cart_qty = (TextView) itemView.findViewById(R.id.cart_qty);
        productOption_type = (TextView) itemView.findViewById(R.id.cart_option);

        //카트 수량 변경 버튼
        qtyPlusButton = (ImageButton) itemView.findViewById(R.id.qty_plus);
        qtyMinusButton= (ImageButton) itemView.findViewById(R.id.qty_minus);

    }
    public void setData(Cart cart) {
        cart_no = cart.getCart_no();
        CartService.loadImage(cart.getProduct_no(), product_imgFile);
        cart_qty.setText(String.valueOf(cart.getCart_qty()));
        product_price.setText(String.valueOf(cart.getProduct_price()));
        product_name.setText(cart.getProduct_name());
        productOption_type.setText(cart.getProductOption_type());

    }
}
