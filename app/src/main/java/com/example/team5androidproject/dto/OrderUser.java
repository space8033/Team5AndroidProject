package com.example.team5androidproject.dto;

public class OrderUser {
    private String user_id; //회원 아이디
    private String users_name;  //회원이름
    private String users_email;	//회원 이메일
    private String users_phone; //회원 전화번호
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "OrderUser{" +
                "user_id='" + user_id + '\'' +
                ", users_name='" + users_name + '\'' +
                ", users_email='" + users_email + '\'' +
                ", users_phone='" + users_phone + '\'' +
                ", point=" + point +
                '}';
    }
}
