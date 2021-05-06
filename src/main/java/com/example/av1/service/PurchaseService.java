package com.example.av1.service;

import com.example.av1.domain.Purchase;
import com.example.av1.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {

    private PurchaseRepository repository;

    public Purchase create(Purchase purchase) {
        return repository.save(purchase);
    }

    public List<Purchase> findAll() {
        return repository.findAll();
    }

}
