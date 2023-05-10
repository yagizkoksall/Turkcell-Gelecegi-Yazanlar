package org.example;

public class VakifBankPosServiceAdapter implements PosService{
    @Override
    public boolean checkPayment() {
        VakifBankPosService vakifBankPosService = new VakifBankPosService();
        return vakifBankPosService.ode();
    }
}
