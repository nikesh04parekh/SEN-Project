package com.practice.senproject.agroplus;

public class pojo_farmer {
    String  name;
    String username;
    String password;
    String birthdate;
    String city;

    public float getLand() {
        return land;
    }

    public void setLand(float land) {
        this.land = land;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    String state;
    float land;
    float income;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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

    public pojo_farmer(){


    }

    public pojo_farmer(String n,String u,String p,String b,String c,String s, float i,float l){
        this.name=n;
        this.username=u;
        this.password=p;
        this.birthdate=b;
        this.city=c;
        this.state=s;
        this.income=i;
        this.land=l;
    }

}
