package org.example;

public class Executive extends Employee{

   private String stockOptions;

    public Executive(int id, String firstName, String lastName, String stockOptions) {
        super(id, firstName, lastName);
        this.stockOptions = stockOptions;
    }

    public String getStockOptions() {
        return stockOptions;
    }

    public void setStockOptions(String stockOptions) {
        this.stockOptions = stockOptions;
    }
}
