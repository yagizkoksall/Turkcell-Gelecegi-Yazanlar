package com.example.paymentservice.business.abstracts;



import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.example.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.example.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.example.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.example.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.example.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.example.paymentservice.business.dto.responses.update.UpdatePaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(UUID id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);
    void delete(UUID id);
    ClientResponse processRentalPayment(CreateRentalPaymentRequest request);

}