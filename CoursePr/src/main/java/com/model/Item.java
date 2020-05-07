package com.model;

import java.util.List;

public class Item {
        private Clothes clothes;
        private Print print;
        private int quantity;
        private double lineSum;

    public Item() {
    }

    public Item(Clothes clothes, Print print, int quantity) {
        this.clothes = clothes;
        this.print = print;
        this.quantity = quantity;
    }


    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Print getPrint() {
        return print;
    }

    public void setPrint(Print print) {
        this.print = print;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLineSum() {
        return lineSum;
    }

    public void setLineSum(double lineSum) {
        this.lineSum = lineSum;
    }
}
