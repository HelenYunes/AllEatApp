package com.example.alleat.Model;
public class Item {

    private String description;
    private String imageURL;
    private String name;
    private String price,id;

    public Item(){}

    public Item(String id,String description, String imageURL, String name, String price) {
        this.id=id;
        this.description = description;
        this.imageURL = imageURL;
        this.name = name;
        this.price = price;
    }
    public Item(String name,String price) {
        this.description = "hamburger with fries";
        this.imageURL =" imageURL";
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return "4";
    }

}
