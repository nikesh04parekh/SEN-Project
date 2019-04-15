package com.practice.senproject.agroplus;

public class pojo_marketyard
{
    private String name;
    private String city;
    private String state;
    private String mi;
    private String ma;

    public pojo_marketyard(String name, String city, String state, String mi, String ma) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.mi = mi;
        this.ma = ma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }
}
