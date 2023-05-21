package com.example.rentalservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;



@Component
public class PaymentClientFallback implements PaymentClient {
    @Override
    public ClientResponse processRentalPayment(CreateRentalPaymentRequest request) {
        throw new RuntimeException("PAYMENT SERVICE IS DOWN");
    }
}
