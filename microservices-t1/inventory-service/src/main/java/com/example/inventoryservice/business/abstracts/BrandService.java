package com.example.inventoryservice.business.abstracts;

import com.example.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateModelResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    // CRUD operations
    List<GetAllBrandsResponse> getAll();

    GetBrandResponse getById(UUID id);

    CreateBrandResponse add(CreateBrandRequest request);


    UpdateBrandResponse update(UUID id, UpdateBrandRequest request);

    void delete(UUID id);

}