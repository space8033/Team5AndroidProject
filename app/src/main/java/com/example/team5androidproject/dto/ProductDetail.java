package com.example.team5androidproject.dto;

import java.util.Arrays;

public class ProductDetail {
    private int product_no;
    private String product_name;
    private int product_price;
    private String product_option;
    private int product_qty;
    private byte[] product_imgFile;

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_option() {
        return product_option;
    }

    public void setProduct_option(String product_option) {
        this.product_option = product_option;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

    public byte[] getProduct_imgFile() {
        return product_imgFile;
    }

    public void setProduct_imgFile(byte[] product_imgFile) {
        this.product_imgFile = product_imgFile;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "product_no=" + product_no +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_option='" + product_option + '\'' +
                ", product_qty=" + product_qty +
                ", product_imgFile=" + Arrays.toString(product_imgFile) +
                '}';
    }
}
