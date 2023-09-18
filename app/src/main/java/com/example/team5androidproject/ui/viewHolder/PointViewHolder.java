package com.example.team5androidproject.ui.viewHolder;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team5androidproject.R;
import com.example.team5androidproject.dto.Point;

public class PointViewHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "PointViewHolder";
    private TextView date;
    private TextView amount;
    private TextView type;
    private TextView orderNo;
    public PointViewHolder(@NonNull View itemView) {
        super(itemView);
        date = (TextView) itemView.findViewById(R.id.txt_change_date);
        amount = (TextView) itemView.findViewById(R.id.txt_amount);
        type = (TextView) itemView.findViewById(R.id.txt_change_type);
        orderNo = (TextView) itemView.findViewById(R.id.txt_order_no);
    }

    public void setData(Point point) {
        date.setText(point.getChangeDate().substring(0, 10));
        int red = Color.RED;
        int blue = Color.BLUE;
        if(point.getChangeType().equals("적립 완료")) {
            amount.setTextColor(blue);
            amount.setText("+" + point.getChangePoint());
        }else if(point.getChangeType().equals("사용 완료")) {
            amount.setTextColor(red);
            amount.setText("-" + point.getChangePoint());
        }
        type.setText(point.getChangeType().substring(0, 2));
        orderNo.setText(String.valueOf(point.getOrderNo()));
    }
}
