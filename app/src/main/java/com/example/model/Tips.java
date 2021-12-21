package com.example.model;

public class Tips {
    private int imvTips;
    private String txtTips;

    public Tips(int imvTips, String txtTips) {
        this.imvTips = imvTips;
        this.txtTips = txtTips;
    }

    public int getImvTips() {
        return imvTips;
    }

    public void setImvTips(int imvTips) {
        this.imvTips = imvTips;
    }

    public String getTxtTips() {
        return txtTips;
    }

    public void setTxtTips(String txtTips) {
        this.txtTips = txtTips;
    }
}
