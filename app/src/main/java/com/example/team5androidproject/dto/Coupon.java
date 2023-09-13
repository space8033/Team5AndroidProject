package com.example.team5androidproject.dto;

public class Coupon {
    private int coupon_no;
    private String coupon_type;

    @Override
    public String toString() {
        return "Coupon{" +
                "coupon_no=" + coupon_no +
                ", coupon_type='" + coupon_type + '\'' +
                ", coupon_value=" + coupon_value +
                ", coupon_condition=" + coupon_condition +
                ", coupon_createdDate='" + coupon_createdDate + '\'' +
                ", coupon_expiredDate='" + coupon_expiredDate + '\'' +
                ", users_users_id='" + users_users_id + '\'' +
                ", coupon_used=" + coupon_used +
                '}';
    }

    public int getCoupon_value() {
        return coupon_value;
    }

    public void setCoupon_value(int coupon_value) {
        this.coupon_value = coupon_value;
    }

    public int getCoupon_condition() {
        return coupon_condition;
    }

    public void setCoupon_condition(int coupon_condition) {
        this.coupon_condition = coupon_condition;
    }

    public String getCoupon_createdDate() {
        return coupon_createdDate;
    }

    public void setCoupon_createdDate(String coupon_createdDate) {
        this.coupon_createdDate = coupon_createdDate;
    }

    public String getCoupon_expiredDate() {
        return coupon_expiredDate;
    }

    public void setCoupon_expiredDate(String coupon_expiredDate) {
        this.coupon_expiredDate = coupon_expiredDate;
    }

    public String getUsers_users_id() {
        return users_users_id;
    }

    public void setUsers_users_id(String users_users_id) {
        this.users_users_id = users_users_id;
    }

    public boolean isCoupon_used() {
        return coupon_used;
    }

    public void setCoupon_used(boolean coupon_used) {
        this.coupon_used = coupon_used;
    }

    private int coupon_value;
    private int coupon_condition;
    private String coupon_createdDate;
    private String coupon_expiredDate;
    private String users_users_id;
    private boolean coupon_used;

    public int getCoupon_no() {
        return coupon_no;
    }

    public void setCoupon_no(int coupon_no) {
        this.coupon_no = coupon_no;
    }

    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }

}
