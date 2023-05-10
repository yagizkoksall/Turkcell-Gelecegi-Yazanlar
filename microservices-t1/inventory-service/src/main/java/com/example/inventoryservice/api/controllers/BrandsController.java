package com.example.inventoryservice.api.controllers;

import com.example.inventoryservice.business.abstracts.BrandService;
import com.example.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService brandService;


    @GetMapping
    public List<GetAllBrandsResponse> getAll(){
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    GetBrandResponse getById(@PathVariable UUID id){
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request){
        return brandService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@PathVariable UUID id, @RequestBody UpdateBrandRequest request){
        return brandService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        brandService.delete(id);
    }
}
