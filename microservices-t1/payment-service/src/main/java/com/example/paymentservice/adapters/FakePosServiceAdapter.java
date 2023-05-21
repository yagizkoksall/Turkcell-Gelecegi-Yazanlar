package com.example.paymentservice.adapters;


import com.example.commonpackage.utils.constants.Messages;
import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.paymentservice.business.abstracts.PosService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {

    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if(!isPaymentSuccessful) throw new BusinessException(Messages.Payment.Failed);
    }
}