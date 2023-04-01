package com.example.rentacar.repository;

import com.example.rentacar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByNameIgnoreCase(String name);
}
