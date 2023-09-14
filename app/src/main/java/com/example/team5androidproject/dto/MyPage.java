package com.example.team5androidproject.dto;

public class MyPage {
    private String name;
    private String created_at;
    private int point;
    private int couponCount;
    private int reviewCount;
    private int inquiryCount;

    @Override
    public String toString() {
        return "MyPage{" +
                "name='" + name + '\'' +
                ", created_at='" + created_at + '\'' +
                ", point=" + point +
                ", couponCount=" + couponCount +
                ", reviewCount=" + reviewCount +
                ", inquiryCount=" + inquiryCount +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(int couponCount) {
        this.couponCount = couponCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getInquiryCount() {
        return inquiryCount;
    }

    public void setInquiryCount(int inquiryCount) {
        this.inquiryCount = inquiryCount;
    }
}
