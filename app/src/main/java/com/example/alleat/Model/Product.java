package com.example.alleat.Model;

class Product {
    private static String name;
    private static double price;
    private static String imgUrl;
    private static String description;

    public Product(String name, double price, String img, String description) {
        this.name = name;
        this.price = price;
        this.imgUrl = img;
        this.description = description;
    }

    public static String getName() {
        return name;
    }

    public static double getPrice() {
        return price;
    }

    public static String getDescription() {
        return description;
    }

    public static String getImgUrl() {
        return imgUrl;
    }

    public static void setDescription(String description) {
        Product.description = description;
    }

    public static void setImgUrl(String imgUrl) {
        Product.imgUrl = imgUrl;
    }

    public static void setName(String name) {
        Product.name = name;
    }

    public static void setPrice(double price) {
        Product.price = price;
    }
}

