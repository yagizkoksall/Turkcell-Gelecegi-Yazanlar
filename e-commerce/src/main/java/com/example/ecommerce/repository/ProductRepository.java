package com.example.ecommerce.repository;

import com.example.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Integer> {

}
