package com.example.model;

public class LineItem {
    private int     icon1;
    private String  nameLine;
    private int     icon2;

    public int getIcon1() {
        return icon1;
    }

    public void setIcon1(int icon1) {
        this.icon1 = icon1;
    }

    public String getNameLine() {
        return nameLine;
    }

    public void setNameLine(String nameLine) {
        this.nameLine = nameLine;
    }

    public int getIcon2() {
        return icon2;
    }

    public void setIcon2(int icon2) {
        this.icon2 = icon2;
    }

    public LineItem(int icon1, String nameLine, int icon2) {
        this.icon1 = icon1;
        this.nameLine = nameLine;
        this.icon2 = icon2;
    }

}
