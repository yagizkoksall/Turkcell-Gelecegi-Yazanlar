package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.BrandService;
import com.example.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.example.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.example.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.example.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.example.rentacar.business.dto.responses.get.GetBrandResponse;
import com.example.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.example.rentacar.business.rules.BrandBusinessRules;
import com.example.rentacar.entities.Brand;
import com.example.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapper mapper;
    private final BrandBusinessRules rules;

    @Override
    @Cacheable(value = "brand_list")
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();
        List<GetAllBrandsResponse> response = brands.
                stream()
                .map(brand -> mapper.map(brand, GetAllBrandsResponse.class)).
                toList();

        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        rules.checkIfBrandExistsById(id);
        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.map(brand, GetBrandResponse.class);

        return response;
    }

    @Override
    @CacheEvict(value = "brand_list",allEntries = true)
    public CreateBrandResponse add(CreateBrandRequest request) {
        rules.checkIfBrandExistsByName(request.getName());
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0);
        repository.save(brand);
        CreateBrandResponse response = mapper.map(brand, CreateBrandResponse.class);
        return response;
    }

    @Override
    @CacheEvict(value = "brand_list",allEntries = true)
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        rules.checkIfBrandExistsById(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        UpdateBrandResponse response = mapper.map(brand, UpdateBrandResponse.class);
        return response;
    }

    @Override
    @CacheEvict(value = "brand_list",allEntries = true)
    public void delete(int id) {
        rules.checkIfBrandExistsById(id);
        repository.deleteById(id);
    }


}
