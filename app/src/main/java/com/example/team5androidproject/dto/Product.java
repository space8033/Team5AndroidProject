package com.example.team5androidproject.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Product implements Serializable {
    private int product_no;
    private String product_name;
    private int product_price;
    private byte[] product_imgFile;
    private List<String> product_option;
    private List<Integer> image_no;

    public List<String> getProduct_option() {
        return product_option;
    }

    public void setProduct_option(List<String> product_option) {
        this.product_option = product_option;
    }

    public List<Integer> getImage_no() {
        return image_no;
    }

    public void setImage_no(List<Integer> image_no) {
        this.image_no = image_no;
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

    @Override
    public String toString() {
        return "Product{" +
                "product_no=" + product_no +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_imgFile=" + Arrays.toString(product_imgFile) +
                ", product_option=" + product_option +
                ", image_no=" + image_no +
                '}';
    }
}
