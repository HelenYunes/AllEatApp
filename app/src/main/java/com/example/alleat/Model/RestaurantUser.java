package com.example.alleat.Model;

public class RestaurantUser {
    private String name;
    private String phone;
    private String password;
    private String payBoxUrl;
    //private boolean isRes;

    public RestaurantUser(String name, String password, String phone, String payBoxUrl) {
        this.name = name;
        this.password = password;
        this.payBoxUrl=payBoxUrl;
        this.phone=phone;
        //   boolean isRes;
    }
    public RestaurantUser() {
        this.name = "";
        this.password = "";
        this.payBoxUrl="";
        this.phone="";
        // this.isRes =false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayBoxUrl() {
        return payBoxUrl;
    }

    public void setPayBoxUrl(String payBoxUrl) {
        this.payBoxUrl = payBoxUrl;
    }
}
