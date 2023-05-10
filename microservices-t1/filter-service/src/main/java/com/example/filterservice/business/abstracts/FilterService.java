package com.example.filterservice.business.abstracts;

import com.example.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.example.filterservice.business.dto.responses.GetFilterResponse;
import com.example.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();
    GetFilterResponse getById(UUID id);
    void add(Filter filter);
    void delete(UUID id);
    void deleteAllByCarId(UUID carId);
    void deleteAllByBrandId(UUID brandId);
    void deleteAllByModelId(UUID modelId);
}
