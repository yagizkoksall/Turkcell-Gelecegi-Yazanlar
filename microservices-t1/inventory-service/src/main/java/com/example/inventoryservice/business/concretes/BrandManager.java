package com.example.inventoryservice.business.concretes;

import com.example.commonpackage.events.inventory.BrandDeletedEvent;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.inventoryservice.business.abstracts.BrandService;
import com.example.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import com.example.inventoryservice.business.rules.BrandBusinessRules;
import com.example.inventoryservice.entities.Brand;
import com.example.inventoryservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapperService mapper;
    private final BrandBusinessRules rules;
    private final KafkaProducer producer;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        var brands = repository.findAll();
        var response = brands
                .stream()
                .map(brand -> mapper.forResponse().map(brand, GetAllBrandsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetBrandResponse getById(UUID id) {
        rules.checkIfBrandExists(id);
        var brand = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(brand, GetBrandResponse.class);

        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        var brand = mapper.forRequest().map(request, Brand.class);
        repository.save(brand);
        var response = mapper.forResponse().map(brand, CreateBrandResponse.class);

        return response;
    }

    @Override
    public UpdateBrandResponse update(UUID id, UpdateBrandRequest request) {
        rules.checkIfBrandExists(id);
        var brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        var response = mapper.forResponse().map(brand, UpdateBrandResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfBrandExists(id);
        repository.deleteById(id);
        producer.sendMessage(new BrandDeletedEvent(id),"brand-deleted");
    }
}
