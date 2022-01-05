package com.example.alleat.Model;

public class Product {
    private  String name;
    private  String price;
    private  String imgUrl;
    private  String description;
    private  String rating;
    private  String restaurant_id;

    public Product  (String name, String price, String img, String description) {
        this.name = name;
        this.price = price;
        this.imgUrl = img;
        this.description = description;
        this.rating = "4";
        this.restaurant_id="2";
    }
    public Product() {

    }
    public Product(String name, String img) {
        this.name = name;
        this.imgUrl = img;

    }

    public  String getName() {
        return name;
    }

    public  String getPrice() {
        return price;
    }

    public  String getDescription() {
        return description;
    }

    public  String getImgUrl() {
        return this.imgUrl;
    }

    public  String getRating() {
        return this.rating;
    }

    public  void setDescription(String description) {
        this.description = description;
    }

    public  void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public  void setRating(String imgUrl) {
        this.rating = rating;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  void setPrice(String price) {
        this.price = price;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}

