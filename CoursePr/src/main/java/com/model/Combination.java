package com.model;

public class Combination {
    private int id;
    private Clothes clothes;
    private Print print;
    private String combinedImageLink;
    private double totalSum;

    public Combination(int id, Clothes clothes, Print print, double totalSum, String combinedImageLink){
        this.id = id;
        this.clothes = clothes;
        this.print = print;
        this.totalSum = totalSum;
        this.combinedImageLink = combinedImageLink;
    }


    public Combination(){    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public String  getCombinedImageLink() {
        return combinedImageLink;
    }

    public void setCombinedImageLink(String combinedImageLink) {
        this.combinedImageLink = combinedImageLink;
    }

}
