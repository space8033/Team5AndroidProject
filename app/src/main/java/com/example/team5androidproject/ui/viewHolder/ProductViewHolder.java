package com.example.team5androidproject.ui.viewHolder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Product;
import com.example.team5androidproject.service.ProductService;
import com.example.team5androidproject.ui.adapter.ProductAdapter;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private int product_no;
    private ImageView product_imgFile;
    private TextView product_name;
    private TextView product_price;

    private static final String TAG = "ListViewHolder";


    public ProductViewHolder(@NonNull View itemView, ProductAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        product_name = (TextView) itemView.findViewById(R.id.product_name);
        product_price = (TextView) itemView.findViewById(R.id.product_price);
        product_imgFile = (ImageView) itemView.findViewById(R.id.product_imgFile);

        itemView.setOnClickListener(v->{
            Log.i(TAG, product_no + "항목 클릭됨");
            //클릭됐을 때 Board객체는 Adapter의 List에서 얻어야한다.
            //getAdapterPosition : List에있는 Index 번호
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });
    }

    public void setData(Product product) {
        product_no = product.getProduct_no();
        ProductService.loadImage(product.getProduct_no(), product_imgFile);
        product_name.setText(product.getProduct_name());
        product_price.setText(String.valueOf(product.getProduct_price()));
    }
}
