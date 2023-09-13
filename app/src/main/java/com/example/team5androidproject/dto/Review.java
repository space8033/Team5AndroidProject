package com.example.team5androidproject.dto;

import java.io.Serializable;
import java.util.List;

public class Review implements Serializable {
    private int review_no;
    private String review_name;
    private int review_rating;
    private String review_title;
    private String review_contents;
    private String review_createdDate;
    private String product_name;
    private List<Integer> images_no;
    private List<byte[]> review_image;

    public int getReview_no() {
        return review_no;
    }

    public void setReview_no(int review_no) {
        this.review_no = review_no;
    }

    public String getReview_name() {
        return review_name;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_no=" + review_no +
                ", review_name='" + review_name + '\'' +
                ", review_rating=" + review_rating +
                ", review_title='" + review_title + '\'' +
                ", review_contents='" + review_contents + '\'' +
                ", review_createdDate='" + review_createdDate + '\'' +
                ", product_name='" + product_name + '\'' +
                ", images_no=" + images_no +
                ", review_image=" + review_image +
                '}';
    }

    public List<Integer> getImages_no() {
        return images_no;
    }

    public void setImages_no(List<Integer> images_no) {
        this.images_no = images_no;
    }

    public void setReview_name(String review_name) {
        this.review_name = review_name;
    }

    public int getReview_rating() {
        return review_rating;
    }

    public void setReview_rating(int review_rating) {
        this.review_rating = review_rating;
    }

    public String getReview_title() {
        return review_title;
    }

    public void setReview_title(String review_title) {
        this.review_title = review_title;
    }

    public String getReview_contents() {
        return review_contents;
    }

    public void setReview_contents(String review_contents) {
        this.review_contents = review_contents;
    }

    public String getReview_createdDate() {
        return review_createdDate;
    }

    public void setReview_createdDate(String review_createdDate) {
        this.review_createdDate = review_createdDate;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public List<byte[]> getReview_image() {
        return review_image;
    }

    public void setReview_image(List<byte[]> review_image) {
        this.review_image = review_image;
    }
}
