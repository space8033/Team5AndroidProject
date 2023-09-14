package com.example.team5androidproject.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ProductDetail implements Serializable {
    private int product_no;
    private String product_name;
    private int product_price;
    private List<String> product_option;
    private int product_qty;
    private List<Integer> images_no;
    private byte[] product_detail_img;
    private List<byte[]> product_detail_thumbnail;

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


    public List<String> getProduct_option() {
        return product_option;
    }

    public void setProduct_option(List<String> product_option) {
        this.product_option = product_option;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

    public List<Integer> getImages_no() {
        return images_no;
    }

    public void setImages_no(List<Integer> images_no) {
        this.images_no = images_no;
    }

    public byte[] getProduct_detail_img() {
        return product_detail_img;
    }

    public void setProduct_detail_img(byte[] product_detail_img) {
        this.product_detail_img = product_detail_img;
    }

    public List<byte[]> getProduct_detail_thumbnail() {
        return product_detail_thumbnail;
    }

    public void setProduct_detail_thumbnail(List<byte[]> product_detail_thumbnail) {
        this.product_detail_thumbnail = product_detail_thumbnail;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "product_no=" + product_no +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_option=" + product_option +
                ", product_qty=" + product_qty +
                ", images_no=" + images_no +
                ", product_detail_img=" + Arrays.toString(product_detail_img) +
                ", product_detail_thumbnail=" + product_detail_thumbnail +
                '}';
    }
}
