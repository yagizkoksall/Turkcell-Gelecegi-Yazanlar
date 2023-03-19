package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {
   private final ProductRepository productRepository;

   public ProductManager(ProductRepository productRepository){
       this.productRepository = productRepository;
   }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(int id) {
        return getById(id);
    }

    @Override
    public Product add(Product product) {
        validateProduct(product);
        return productRepository.add(product);
    }

    @Override
    public Product update(int id, Product product) {
       validateProduct(product);
        return productRepository.update(id,product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    //! Business rules

    private void validateProduct(Product product){
        checkIfUnitPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionLengthValid(product);
    }

    private void checkIfDescriptionLengthValid(Product product) {
        if(product.getDescription().length() < 10 || product.getDescription().length() > 50)
            throw new IllegalArgumentException("Description length must be between 10 and 50 characters.");
    }

    private void checkIfQuantityValid(Product product) {
        if(product.getQuantity() < 0) throw new IllegalArgumentException("Quantity cannot be less than zero");
    }

    private void checkIfUnitPriceValid(Product product) {
        if(product.getUnitPrice() <= 0)
            throw new IllegalArgumentException("Price cannot be less than or equal to zero.");
    }
}
