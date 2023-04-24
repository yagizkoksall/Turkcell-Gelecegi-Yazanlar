package com.example.rentacar.business.rules;

import com.example.rentacar.common.constants.Messages;
import com.example.rentacar.core.exceptions.BusinessException;
import com.example.rentacar.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    public void checkIfInvoiceExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Invoice.NotFound);
        }
    }
}
