package com.example.alleat.Model;

import java.util.List;

public class Payment {

    private String name;
    private  String total;
    private List<Order> foods;

    public Payment(String name, String total, List<Order> foods){

        this.name = name;
        this.total = total;
        this.foods = foods;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
