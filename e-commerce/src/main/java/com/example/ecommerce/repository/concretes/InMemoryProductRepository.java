package com.example.ecommerce.repository.concretes;

import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    List<Product> products = new ArrayList<>();

    public InMemoryProductRepository() {
        products.add(new Product(1,"Macbook",12,12000,"On numara macbook"));
        products.add(new Product(2,"Playstation",8,8000,"Son model playstation"));
        products.add(new Product(3,"Iphone 14",24,30000,"Canavar Playstation"));
        products.add(new Product(4,"Xbox",5,25000,"microsoft's product"));
        products.add(new Product(5,"Dyson v15",10,14999.99,"dyson's product"));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getById(int id) {
       return products.get(id-1);
    }

    @Override
    public Product add(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product update(int id, Product product) {
       return products.set(id-1,product);
    }

    @Override
    public void delete(int id) {
        products.remove(id-1);
    }
}
