package com.practice.senproject.agroplus;

public class disp_AgroShop
{
    private String shopName;
    private Long contact;
    private String city;

    disp_AgroShop()
    {

    }
    public disp_AgroShop(String shopName, Long contact, String city) {
        this.shopName = shopName;
        this.contact = contact;
        this.city = city;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
