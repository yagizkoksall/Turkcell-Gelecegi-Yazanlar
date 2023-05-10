package org.example;

public class IsBankPosServiceAdapter implements PosService{

    @Override
    public boolean checkPayment() {

        IsBankPosService isBankPosService = new IsBankPosService();
        return isBankPosService.pay();
    }
}
