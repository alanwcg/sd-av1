package com.example.av1.service;

import com.example.av1.domain.Product;
import com.example.av1.exceptions.AppException;
import com.example.av1.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;

    public Product create(Product product) {
        Optional<Product> p = repository.findByNameAndDeletedIsFalse(product.getName());

        if (p.isPresent()) {
            throw new AppException("Product already exists");
        }

        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new AppException("Product not found"));
    }

    public Product update(Product product) {
        return repository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        product.setDeleted(true);

        repository.save((product));
    }

}
