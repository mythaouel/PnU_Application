package com.example.model;

public class Customer {
    private int cusId;
    private String cusName;
    private Double cusBirthday;
    private String cusEmai;

    public Customer(int cusId, String cusName, Double cusBirthday, String cusEmai, int cusPhone, String cusAddress, byte[] cusAvatar) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusBirthday = cusBirthday;
        this.cusEmai = cusEmai;
        this.cusPhone = cusPhone;
        this.cusAddress = cusAddress;
        this.cusAvatar = cusAvatar;
    }

    private int   cusPhone;
    private String cusAddress;
    private byte[] cusAvatar;

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Double getCusBirthday() {
        return cusBirthday;
    }

    public void setCusBirthday(Double cusBirthday) {
        this.cusBirthday = cusBirthday;
    }

    public String getCusEmai() {
        return cusEmai;
    }

    public void setCusEmai(String cusEmai) {
        this.cusEmai = cusEmai;
    }

    public int getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(int cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public byte[] getCusAvatar() {
        return cusAvatar;
    }

    public void setCusAvatar(byte[] cusAvatar) {
        this.cusAvatar = cusAvatar;
    }
}
