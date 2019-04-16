package com.practice.senproject.agroplus;

public class pojo_mysubsidy {
    private String subsidyname;
    private float status;
    private float amount;

    public pojo_mysubsidy(){

    }

    public String getSubsidyname() {
        return subsidyname;
    }

    public void setSubsidyname(String subsidyname) {
        this.subsidyname = subsidyname;
    }

    public float getStatus() {
        return status;
    }

    public void setStatus(float status) {
        this.status = status;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }



    public pojo_mysubsidy(String subsidyname, float status, float amount) {
        this.subsidyname = subsidyname;
        this.status = status;
        this.amount = amount;
    }
}
