package com.example.rentalservice.api.clients;


import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE", fallback = PaymentClientFallback.class)
public interface PaymentClient {

    @Retry(name = "retry-payment")
    @PostMapping(value = "/api/payments/process-rental-payment")
    ClientResponse processRentalPayment(@RequestBody CreateRentalPaymentRequest request);
}
