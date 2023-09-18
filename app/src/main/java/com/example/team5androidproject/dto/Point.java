package com.example.team5androidproject.dto;

public class Point {
    private String changeDate;
    private String changePoint;
    private String changeType;
    private int orderNo;

    @Override
    public String toString() {
        return "Point{" +
                "changeDate='" + changeDate + '\'' +
                ", changePoint='" + changePoint + '\'' +
                ", changeType='" + changeType + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangePoint() {
        return changePoint;
    }

    public void setChangePoint(String changePoint) {
        this.changePoint = changePoint;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}
