package com.model;

import java.util.Date;

public class Order {
    private int id;
    private String date;
    private int client;
    private String status;
    private String email;
    private String address;
    private double cost;

    public Order(int id, String date, int client, String status, String email, String address, double cost) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.status = status;
        this.email = email;
        this.address = address;
        this.cost = cost;
    }

    public Order(String date, int client, String status, String email, String address, double cost) {
        this.date = date;
        this.client = client;
        this.status = status;
        this.email = email;
        this.address = address;
        this.cost = cost;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
