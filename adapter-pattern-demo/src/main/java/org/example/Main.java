package org.example;

public class Main {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager(new IsBankPosServiceAdapter());
        OrderManager orderManager1 = new OrderManager(new VakifBankPosServiceAdapter());


        orderManager.makePayment();
        orderManager1.makePayment();
    }
}