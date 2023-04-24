package com.example.rentacar.business.abstracts;

import com.example.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.example.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.example.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.example.rentacar.business.dto.responses.get.GetAllInvoicesResponse;
import com.example.rentacar.business.dto.responses.get.GetInvoiceResponse;
import com.example.rentacar.business.dto.responses.update.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(int id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request);
    void delete(int id);
}
