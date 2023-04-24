package com.example.rentacar.business.abstracts;

import com.example.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.example.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.example.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.example.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.example.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.example.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.example.rentacar.common.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    void delete(int id);
    void processRentalPayment(CreateRentalPaymentRequest request);

}
