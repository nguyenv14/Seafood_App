package com.nhanlovecode.doancuoiky.Models;

public class Wards {
    int xaid;
    String name_ward;
    String type;
    int maqh;

    public Wards() {
    }

    public Wards(int xaid, String name_ward, String type, int maqh) {
        this.xaid = xaid;
        this.name_ward = name_ward;
        this.type = type;
        this.maqh = maqh;
    }

    public int getXaid() {
        return xaid;
    }

    public void setXaid(int xaid) {
        this.xaid = xaid;
    }

    public String getName_ward() {
        return name_ward;
    }

    public void setName_ward(String name_ward) {
        this.name_ward = name_ward;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaqh() {
        return maqh;
    }

    public void setMaqh(int maqh) {
        this.maqh = maqh;
    }
}
