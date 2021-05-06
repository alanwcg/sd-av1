package com.example.av1.service;

import com.example.av1.domain.PurchaseProduct;
import com.example.av1.repository.PurchaseProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseProductService {

    private PurchaseProductRepository repository;

    public void create(List<PurchaseProduct> purchaseProducts) {
        repository.saveAll(purchaseProducts);
    }

}
