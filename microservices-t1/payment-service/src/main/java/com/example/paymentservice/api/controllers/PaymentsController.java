package com.example.paymentservice.api.controllers;


import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.example.paymentservice.business.abstracts.PaymentService;
import com.example.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.example.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.example.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.example.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.example.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.example.paymentservice.business.dto.responses.update.UpdatePaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable UUID id, @Valid @RequestBody UpdatePaymentRequest request){
        return service.update(id,request);
    }

    @PostMapping("/process-rental-payment")
    public ClientResponse processRentalPayment(@RequestBody CreateRentalPaymentRequest request){
        return service.processRentalPayment(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}