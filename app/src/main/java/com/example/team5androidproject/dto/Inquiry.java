package com.example.team5androidproject.dto;

public class Inquiry {
    private int inquriyNo;
    private int productNo;
    private int inquriyType;
    private String productName;
    private String createdAt;
    private String inquriyTitle;

    @Override
    public String toString() {
        return "Inquiry{" +
                "inquriyNo=" + inquriyNo +
                ", productNo=" + productNo +
                ", inquriyType=" + inquriyType +
                ", productName='" + productName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", inquriyTitle='" + inquriyTitle + '\'' +
                '}';
    }

    public int getInquriyNo() {
        return inquriyNo;
    }

    public void setInquriyNo(int inquriyNo) {
        this.inquriyNo = inquriyNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getInquriyType() {
        return inquriyType;
    }

    public void setInquriyType(int inquriyType) {
        this.inquriyType = inquriyType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getInquriyTitle() {
        return inquriyTitle;
    }

    public void setInquriyTitle(String inquriyTitle) {
        this.inquriyTitle = inquriyTitle;
    }
}
