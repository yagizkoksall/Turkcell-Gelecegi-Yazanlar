package com.example.rentacar.repository;

import com.example.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BrandRepository extends JpaRepository<Brand,Integer> {
    // Custom queries
}
