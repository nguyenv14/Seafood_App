package com.nhanlovecode.doancuoiky.Models;

public class Province {
    int maqh;
    String name_province;
    String type;
    int matp;

    public Province() {}


    public Province(int maqh, String name_province, String type, int matp) {
        this.maqh = maqh;
        this.name_province = name_province;
        this.type = type;
        this.matp = matp;
    }

    public int getMaqh() {
        return maqh;
    }

    public void setMaqh(int maqh) {
        this.maqh = maqh;
    }

    public String getName_province() {
        return name_province;
    }

    public void setName_province(String name_province) {
        this.name_province = name_province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMatp() {
        return matp;
    }

    public void setMatp(int matp) {
        this.matp = matp;
    }
}
