package com.example.alleat.Model;

public class Customer {
    private String name;
    private String password;
    //private boolean isRes;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
     //   boolean isRes;
    }
    public Customer() {
        this.name = "";
        this.password = "";
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
}
