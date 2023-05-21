package com.nhanlovecode.doancuoiky.Models;

public class City {
    int matp;
    String name_city;
    String type;


    public City() {}

    public City(int matp, String name_city, String type) {
        this.matp = matp;
        this.name_city = name_city;
        this.type = type;
    }

    public int getMatp() {
        return matp;
    }

    public void setMatp(int matp) {
        this.matp = matp;
    }

    public String getName_city() {
        return name_city;
    }

    public void setName_city(String name_city) {
        this.name_city = name_city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
