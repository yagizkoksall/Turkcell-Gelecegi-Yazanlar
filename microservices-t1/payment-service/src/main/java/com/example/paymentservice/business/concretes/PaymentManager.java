package com.example.paymentservice.business.concretes;


import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.paymentservice.business.abstracts.PaymentService;
import com.example.paymentservice.business.abstracts.PosService;
import com.example.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.example.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.example.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.example.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.example.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.example.paymentservice.business.dto.responses.update.UpdatePaymentResponse;
import com.example.paymentservice.business.rules.PaymentBusinessRules;
import com.example.paymentservice.entities.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;
    private final PaymentBusinessRules rules;


    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentsResponse> response = payments
                .stream()
                .map(payment -> mapper.map(payment, GetAllPaymentsResponse.class)).toList();

        return response;
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        rules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.map(payment, GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request);

        Payment payment = mapper.map(request, Payment.class);
        payment.setId(null);
        repository.save(payment);
        CreatePaymentResponse response = mapper.map(payment, CreatePaymentResponse.class);

        return response;
    }


    @Override
    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.map(payment, UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public ClientResponse processRentalPayment(CreateRentalPaymentRequest request) {
        ClientResponse response = new ClientResponse();
        try{
            rules.checkIfPaymentIsValid(request);
            Payment payment = repository.findByCardNumber(request.getCardNumber());
            rules.checkIfBalanceIsEnough(request.getPrice(), payment.getBalance());
            posService.pay();
            // fake pos service
            payment.setBalance(payment.getBalance() - request.getPrice());
            repository.save(payment);
            response.setSuccess(true);
        }
        catch (BusinessException exception){
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }

        return response;


    }


}