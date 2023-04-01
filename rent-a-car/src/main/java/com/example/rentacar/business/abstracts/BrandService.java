package com.example.rentacar.business.abstracts;

import com.example.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.example.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.example.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.example.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.example.rentacar.business.dto.responses.get.GetBrandResponse;
import com.example.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.example.rentacar.entities.Brand;

import java.util.List;

public interface BrandService {
    // CRUD operations
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(int id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(int id, UpdateBrandRequest request);
    void delete(int id);

}
