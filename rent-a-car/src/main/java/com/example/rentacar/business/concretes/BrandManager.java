package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.BrandService;
import com.example.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.example.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.example.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.example.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.example.rentacar.business.dto.responses.get.GetBrandResponse;
import com.example.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.example.rentacar.entities.Brand;
import com.example.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandsResponse> response = brands.
                stream()
                .map(brand -> mapper.map(brand,GetAllBrandsResponse.class)).
                toList();

        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExistsById(id);
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.map(brand,GetBrandResponse.class);

        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        checkIfBrandExistsByName(request.getName());
        Brand brand = mapper.map(request,Brand.class);
        brand.setId(0);
        brandRepository.save(brand);
        CreateBrandResponse response = mapper.map(brand,CreateBrandResponse.class);
        return response;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        checkIfBrandExistsById(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        brandRepository.save(brand);
        UpdateBrandResponse response = mapper.map(brand, UpdateBrandResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfBrandExistsById(id);
        brandRepository.deleteById(id);
    }

    // Business rules

    private void checkIfBrandExistsById(int id){
        if(!brandRepository.existsById(id)) throw new IllegalArgumentException("Böyle bir marka mevcut değil.");
    }

    private void checkIfBrandExistsByName(String name){
        if(brandRepository.existsByNameIgnoreCase(name)){
            throw new RuntimeException("Böyle bir marka sistemde kayıtlı!");
        }
    }
}
