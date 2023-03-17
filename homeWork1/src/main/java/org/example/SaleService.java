package org.example;

public class SaleService {
    public void createSale(Sale sale){
        System.out.println(sale.getGame().getName() + " " + sale.getUser().getFirstName() + " Created.");
        System.out.println("Sale created for "+ getSalePrice(sale) + " USD");
    }
    public void updateSale(Sale sale){
        System.out.println(sale.getId() + " Updated.");
    }
    public void deleteSale(Sale sale){
        System.out.println(sale.getId() + " Deleted.");
    }
    private double getSalePrice(Sale sale){
        if (sale.getOffer()!=null) {
            return sale.getGame().getUnitPrice() - sale.getOffer().getDiscount();

        }
        return sale.getGame().getUnitPrice();
    }
}
