package org.example;

public class OrderManager {
    PosService posService;

    public OrderManager(PosService posService) {
        this.posService = posService;
    }

    public void makePayment(){
        //business kodları

        if((posService.checkPayment())){
            System.out.println("Payment successful");
        }
        else{
            System.out.println("Payment failure");
        }

    }
}
