package com.example.model;

public class User {
    private String userId;
    private String userName;
    private String phone;
    private String userPassword;
    private String OTP;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(String userId, String userName, String phone, String userPassword, String OTP, String status) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.userPassword = userPassword;
        this.OTP = OTP;
        this.status = status;
    }
}


