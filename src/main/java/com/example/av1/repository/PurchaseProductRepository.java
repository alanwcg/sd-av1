package com.example.av1.repository;

import com.example.av1.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

}
