package com.example.team5androidproject.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.OrderHistory;
import com.example.team5androidproject.service.OrderService;

import java.text.DecimalFormat;

public class HistoryDetailViewHolder extends RecyclerView.ViewHolder{
    private TextView deliveryStatus;
    private TextView arriveAt;
    private ImageView productImage;
    private TextView productTitle;
    private TextView priceQuantity;

    public HistoryDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        deliveryStatus = (TextView) itemView.findViewById(R.id.txt_delivery_status);
        arriveAt = (TextView) itemView.findViewById(R.id.txt_arrive_at);
        productImage = (ImageView) itemView.findViewById(R.id.product_image);
        productTitle = (TextView) itemView.findViewById(R.id.txt_product_title);
        priceQuantity = (TextView) itemView.findViewById(R.id.txt_price_quantity);
    }

    public void setCard(OrderHistory orderHistory) {
        deliveryStatus.setText(orderHistory.getDeliveryStatus());
        arriveAt.setText(orderHistory.getArrival());
        OrderService.loadImage(orderHistory.getProductNo(), productImage);
        productTitle.setText(orderHistory.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        priceQuantity.setText(decimalFormat.format(orderHistory.getPrice()) + "원 * " + orderHistory.getQuantity() + "개");
    }
}
