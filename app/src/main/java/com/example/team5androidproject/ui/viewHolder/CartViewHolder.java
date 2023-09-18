package com.example.team5androidproject.ui.viewHolder;

import android.icu.text.DecimalFormat;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    public TextView product_price;
    private ImageView product_imgFile;
    private TextView productOption_type;
    private TextView new_qty;
    public ImageButton qtyPlusButton;
    public ImageButton qtyMinusButton;
    public CheckBox checkBox;
    /*public TextView selectedItemCountText;*/
    public ImageButton deleteOneButton;



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
        checkBox = (CheckBox) itemView.findViewById(R.id.check_item); // CheckBox 초기화

        //카트 지우기 버튼
        deleteOneButton = (ImageButton) itemView.findViewById(R.id.delete_Item);
        //카트 전체 지우기 버튼
    }
    public void setData(Cart cart) {
        cart_no = cart.getCart_no();
        CartService.loadImage(cart.getProduct_no(), product_imgFile);
        cart_qty.setText(String.valueOf(cart.getCart_qty()));
        DecimalFormat df =new DecimalFormat("#,###");
        product_price.setText(String.valueOf(df.format(cart.getProduct_price() * cart.getCart_qty())));
        product_name.setText(cart.getProduct_name());
        productOption_type.setText(cart.getProductOption_type());

        //체크박스 상태 설정
        checkBox.setChecked(cart.isSelected());
    }
}
