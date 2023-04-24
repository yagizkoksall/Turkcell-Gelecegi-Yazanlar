package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.InvoiceService;
import com.example.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.example.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.example.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.example.rentacar.business.dto.responses.get.GetAllInvoicesResponse;
import com.example.rentacar.business.dto.responses.get.GetInvoiceResponse;
import com.example.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
import com.example.rentacar.business.rules.InvoiceBusinessRules;
import com.example.rentacar.entities.Invoice;
import com.example.rentacar.entities.Rental;
import com.example.rentacar.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapper mapper;
    private final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> response = invoices
                .stream()
                .map(invoice -> mapper.map(invoice, GetAllInvoicesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.map(invoice, GetInvoiceResponse.class);

        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(invoice));
        CreateInvoiceResponse response = mapper.map(invoice, CreateInvoiceResponse.class);

        return response;
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(id);
        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.map(invoice, UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }


    private double getTotalPrice(Invoice invoice) {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }
}
