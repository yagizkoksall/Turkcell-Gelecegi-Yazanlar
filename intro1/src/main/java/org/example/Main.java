package org.example;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product(1, "Iphone 14", 30000, "test"
                , 10
        );// TODO:toString


        Product product2 = new Product(2, "Samsung Galaxy S21", 20000,
                "Telefon2", 10);

        Product product3 = new Product(3, "PS3", 15000, "Playstation",
                15);
        Product[] products = {product1, product2, product3};

        for (Product product : products) {
            System.out.println(product);
        }

//        for (Product product : products) {
//            System.out.println(product.getName() + ": " + product.getUnitPrice() +
//                    " %" + product.getDiscount() +
//                    " uygulandıktan sonraki birim fiyatı: " +
//                    product.getUnitPriceAfterDiscount());
//        }

    }
}