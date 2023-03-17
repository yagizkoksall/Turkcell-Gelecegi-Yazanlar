package org.example;

public class Offer {
    private int id;
    private double discount;

    public Offer() {
    }

    public Offer(int id, double discount) {
        this.id = id;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
