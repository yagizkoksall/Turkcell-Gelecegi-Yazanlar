package com.example.rentacar.business.rules;

import com.example.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.example.rentacar.common.constants.Messages;
import com.example.rentacar.common.dto.CreateRentalPaymentRequest;
import com.example.rentacar.core.exceptions.BusinessException;
import com.example.rentacar.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Payment.NotFound);
        }
    }

    public void checkIfCardExists(CreatePaymentRequest request) {
        if (repository.existsByCardNumber(request.getCardNumber())) {
            throw new BusinessException(Messages.Payment.CardNumberAlreadyExists);
        }
    }

    public void checkIfPaymentIsValid(CreateRentalPaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )) {
            throw new BusinessException(Messages.Payment.NotAValidPayment);
        }
    }

    public void checkIfBalanceIsEnough(double price, double balance) {
        if (balance < price) {
            throw new BusinessException(Messages.Payment.NotEnoughMoney);
        }

    }
}
