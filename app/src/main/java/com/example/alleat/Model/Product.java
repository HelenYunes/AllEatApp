package com.example.alleat.Model;

public class Product {
    private  String description;
    private  String id;
    private  String imageURL;
    private  String name;
    private  String price;
    private  String rating;


    public Product  (String description, String restaurant_id, String img, String name,String price) {
        this.name = name;
        this.price = price;
        this.imageURL = img;
        this.description = description;
        this.rating = "4";
        this.id =restaurant_id;
    }
    public Product() {

    }
    public Product(String name, String img) {
        this.name = name;
        this.imageURL = img;

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

    public  String getImageURL() {
        return this.imageURL;
    }

    public  String getRating() {
        return this.rating;
    }

    public  void setDescription(String description) {
        this.description = description;
    }

    public  void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

