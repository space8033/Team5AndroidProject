package com.example.team5androidproject.dto;

public class OrderHistory {
    private String deliveryStatus;
    private String orderDate;
    private String arrival;
    private int productNo;
    private String productName;
    private int price;
    private int quantity;

    @Override
    public String toString() {
        return "OrderHistory{" +
                "deliveryStatus='" + deliveryStatus + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", arrival='" + arrival + '\'' +
                ", productNo=" + productNo +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
