package com.example.invoiceservice.business.rules;


import com.example.commonpackage.utils.constants.Messages;
import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.invoiceservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    public void checkIfInvoiceExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Invoice.NotFound);
        }
    }
}
