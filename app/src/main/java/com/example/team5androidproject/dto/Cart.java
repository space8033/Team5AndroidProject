package com.example.team5androidproject.dto;

import java.util.Arrays;

public class Cart {
    private int cart_no;
    private int cart_qty;
    private String product_name;
    private int product_price;
    private byte[] product_imgFile;
    private String productOption_type;
    private int product_no;
    private boolean selected; // 선택 상태를 나타내는 필드 추가

    public int getCart_no() {
        return cart_no;
    }

    public void setCart_no(int cart_no) {
        this.cart_no = cart_no;
    }

    public int getCart_qty() {
        return cart_qty;
    }

    public void setCart_qty(int cart_qty) {
        this.cart_qty = cart_qty;
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

    public byte[] getProduct_imgFile() {
        return product_imgFile;
    }

    public void setProduct_imgFile(byte[] product_imgFile) {
        this.product_imgFile = product_imgFile;
    }

    public String getProductOption_type() {
        return productOption_type;
    }

    public void setProductOption_type(String productOption_type) {
        this.productOption_type = productOption_type;
    }

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    // 다른 필드의 getter 및 setter 메서드들

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart_no=" + cart_no +
                ", cart_qty=" + cart_qty +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_imgFile=" + Arrays.toString(product_imgFile) +
                ", productOption_type='" + productOption_type + '\'' +
                ", product_no=" + product_no +
                ", selected=" + selected +
                '}';
    }
}
