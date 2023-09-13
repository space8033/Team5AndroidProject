package com.example.team5androidproject.dto;

import java.util.Arrays;

public class Cart {
    private int cart_no;
    private String production_productoption_no;
    private int cart_qty;
    private int product_no;
    private String product_name;
    private int product_price;
    private byte[] product_imgFile;
    private int productOption_no;
    private String productOption_type;

    public int getCart_no() {
        return cart_no;
    }

    public void setCart_no(int cart_no) {
        this.cart_no = cart_no;
    }

    public String getProduction_productoption_no() {
        return production_productoption_no;
    }

    public void setProduction_productoption_no(String production_productoption_no) {
        this.production_productoption_no = production_productoption_no;
    }

    public int getCart_qty() {
        return cart_qty;
    }

    public void setCart_qty(int cart_qty) {
        this.cart_qty = cart_qty;
    }

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

    public byte[] getProduct_imgFile() {
        return product_imgFile;
    }

    public void setProduct_imgFile(byte[] product_imgFile) {
        this.product_imgFile = product_imgFile;
    }

    public int getProductOption_no() {
        return productOption_no;
    }

    public void setProductOption_no(int productOption_no) {
        this.productOption_no = productOption_no;
    }

    public String getProductOption_type() {
        return productOption_type;
    }

    public void setProductOption_type(String productOption_type) {
        this.productOption_type = productOption_type;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart_no=" + cart_no +
                ", production_productoption_no='" + production_productoption_no + '\'' +
                ", cart_qty=" + cart_qty +
                ", product_no=" + product_no +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_imgFile=" + Arrays.toString(product_imgFile) +
                ", productOption_no=" + productOption_no +
                ", productOption_type='" + productOption_type + '\'' +
                '}';
    }
}
