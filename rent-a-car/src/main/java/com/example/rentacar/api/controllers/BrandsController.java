package com.example.rentacar.api.controllers;

import com.example.rentacar.business.abstracts.BrandService;
import com.example.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.example.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.example.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.example.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.example.rentacar.business.dto.responses.get.GetBrandResponse;
import com.example.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.example.rentacar.entities.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/brands")
public class BrandsController {
   private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<GetAllBrandsResponse> getAll(){
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    GetBrandResponse getById(@PathVariable int id){
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request){
        return brandService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest request){
        return brandService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }
}
