package org.example;

public class Product {

    private int id;
    private String name;
    private double unitPrice;
    private String description;
    private double discount;

    public Product() {
    }

    public Product(int id, String name, double unitPrice, String description,
                   double discount) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name + "!";
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getUnitPriceAfterDiscount() {
        return getUnitPrice() - ((unitPrice * discount) / 100);
    }

    @Override
    public String toString(){
        return id + " - " + name + " - " + unitPrice;
    }

}
