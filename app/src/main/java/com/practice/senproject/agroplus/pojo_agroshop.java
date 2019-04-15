package com.practice.senproject.agroplus;

public class pojo_agroshop
{
    private String name;
    private Long contact;
    private String city;

    pojo_agroshop()
    {

    }
    public pojo_agroshop(String name, Long contact, String city) {
        this.name = name;
        this.contact = contact;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
