package com.example.ecommerce.entities;

import com.example.ecommerce.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;
    private double unitPrice;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "product_category",
//            joinColumns = @JoinColumn(name = "category_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
    private Set<Category> categories = new HashSet<>();


}
