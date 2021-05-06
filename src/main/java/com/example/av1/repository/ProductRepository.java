package com.example.av1.repository;

import com.example.av1.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByIdAndDeletedIsFalse(Long id);

    public Optional<Product> findByNameAndDeletedIsFalse(String name);

}
