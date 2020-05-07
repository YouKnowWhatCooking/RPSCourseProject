package com.model;

public class Template {
    private int id;
    private String location;
    private double price;

    public Template(int id, String location, double price){
        this.id = id;
        this.location = location;
        this.price = price;
    }

    public Template(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
