package com.example.team5androidproject.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Inquiry;
import com.example.team5androidproject.service.ProductService;

public class InquiryViewHolder extends RecyclerView.ViewHolder {
    private TextView inquiryType;
    private TextView inquiryProduct;
    private TextView inquiryCreatedAt;
    private TextView inquiryTitle;
    private ImageView productImage;

    public InquiryViewHolder(@NonNull View itemView) {
        super(itemView);
        inquiryType = (TextView) itemView.findViewById(R.id.txt_inquiry_type);
        inquiryProduct = (TextView) itemView.findViewById(R.id.txt_inquiry_product);
        inquiryCreatedAt = (TextView) itemView.findViewById(R.id.txt_inquiry_created);
        inquiryTitle = (TextView) itemView.findViewById(R.id.txt_inquiry_title);
        productImage = (ImageView) itemView.findViewById(R.id.inquiry_image);
    }

    public void setData(Inquiry inquiry) {
        if(inquiry.getInquriyType() == 1) {
            inquiryType.setText("크기");
        }else if(inquiry.getInquriyType() == 2) {
            inquiryType.setText("배송");
        } else if(inquiry.getInquriyType() == 3) {
            inquiryType.setText("재입고");
        } else if(inquiry.getInquriyType() == 4) {
            inquiryType.setText("상품상세문의");
        } else if(inquiry.getInquriyType() == 5) {
            inquiryType.setText("기타");
        }

        inquiryProduct.setText(inquiry.getProductName());
        inquiryCreatedAt.setText(inquiry.getCreatedAt());
        inquiryTitle.setText(inquiry.getInquriyTitle());

        ProductService.loadImage(inquiry.getProductNo(), productImage);
    }

}
