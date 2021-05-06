package com.example.av1.resource.creditCard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/register/credit-card")
public class CreditCardResource {

    @PostMapping
    public ResponseEntity<Void> registerCreditCard(@RequestBody CreditCardDTO dto) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForLocation("http://localhost:8081/credit-card", dto);

        return ResponseEntity.noContent().build();
    }

}
