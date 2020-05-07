package com.model;

public class Clothes {
    private int id;
    private String title;
    private String type;
    private String imageLink;
    private double price;

    public Clothes(int id, String title, String type, String imageLink, double price) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.imageLink = imageLink;
        this.price = price;
    }

    public Clothes(String title, String type, String imageLink, double price) {
        this.title = title;
        this.type = type;
        this.imageLink = imageLink;
        this.price = price;
    }

    public Clothes() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
