package com.example.model;

public class SpNoiBat {
    private int imvHinh;
    private String txtTen;

    public SpNoiBat(int imvHinh, String txtTen) {
        this.imvHinh = imvHinh;
        this.txtTen = txtTen;
    }

    public int getImvHinh() {
        return imvHinh;
    }

    public void setImvHinh(int imvHinh) {
        this.imvHinh = imvHinh;
    }

    public String getTxtTen() {
        return txtTen;
    }

    public void setTxtTen(String txtTen) {
        this.txtTen = txtTen;
    }
}
