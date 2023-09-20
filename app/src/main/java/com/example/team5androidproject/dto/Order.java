package com.example.team5androidproject.dto;

public class Order {
    private String user_id; //회원 아이디
    private String users_name;  //회원이름
    private String users_email;	//회원 이메일
    private String users_phone;
    private int cart_no; // 카트번호
    private int cart_qty; // 카트 수량
    private String product_name; //상품명
    private int product_price; //상품 가격
    private String productOption_type; //상품 옵션 이름
    private int product_no; //상품 번호
    private int point; // 사용자 소유 포인트

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getUsers_email() {
        return users_email;
    }

    public void setUsers_email(String users_email) {
        this.users_email = users_email;
    }

    public String getUsers_phone() {
        return users_phone;
    }

    public void setUsers_phone(String users_phone) {
        this.users_phone = users_phone;
    }

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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user_id='" + user_id + '\'' +
                ", users_name='" + users_name + '\'' +
                ", users_email='" + users_email + '\'' +
                ", users_phone='" + users_phone + '\'' +
                ", cart_no=" + cart_no +
                ", cart_qty=" + cart_qty +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", productOption_type='" + productOption_type + '\'' +
                ", product_no=" + product_no +
                ", point=" + point +
                '}';
    }
}
