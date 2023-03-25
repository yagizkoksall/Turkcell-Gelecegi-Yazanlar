package com.example.rentacar.api.controllers;

import com.example.rentacar.business.abstracts.BrandService;
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
    List<Brand> getAll(){
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    Brand getById(@PathVariable int id){
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Brand add(@RequestBody Brand brand){
        return brandService.add(brand);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable int id,@RequestBody Brand brand){
        return brandService.update(id,brand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }
}
