package com.example.team5androidproject.dto;

public class Login {
    private String userId;
    private String password;
    private String result;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
