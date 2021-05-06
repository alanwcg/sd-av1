package com.example.av1.resource.purchase;

import com.example.av1.domain.*;
import com.example.av1.resource.purchase.dto.*;
import com.example.av1.service.PurchaseProductService;
import com.example.av1.service.PurchaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/purchases")
@AllArgsConstructor
public class PurchaseResource {

    private PurchaseService service;
    private PurchaseProductService purchaseProductService;

    @PostMapping
    public ResponseEntity<PurchaseDTO> create(@Valid @RequestBody CreateOrUpdatePurchaseDTO dto) {
        RestTemplate restTemplate = new RestTemplate();

        RequestTokenDTO requestTokenBody = Optional.of(dto)
                .map(CreateOrUpdatePurchaseDTO::toRequestTokenBody).get();

        ResponseEntity<String> requestTokenResponse = restTemplate.postForEntity("http://localhost:8081/credit-card/request-token",
                requestTokenBody, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode token = null;
        try {
            JsonNode response = mapper.readTree(requestTokenResponse.getBody());
            token = response.get("token");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        InvoiceDTO invoice = Optional.of(dto)
                .map(CreateOrUpdatePurchaseDTO::toInvoice).get();
        invoice.setCreditCardToken(token.asText());
        invoice.setEstablishment("Games Store");

        restTemplate.postForLocation("http://localhost:8081/invoice",
                invoice);


        Purchase purchase = Optional.of(dto)
                .map(CreateOrUpdatePurchaseDTO::to)
                .map(service::create).get();

        List<PurchaseProduct> purchaseProducts = new ArrayList<>();

        for (PurchaseProductDTO product: dto.getProducts()) {
            CreateOrUpdatePurchaseProductDTO purchaseProductDTO = new CreateOrUpdatePurchaseProductDTO();

            purchaseProductDTO.setPurchaseId(purchase.getId());
            purchaseProductDTO.setProductId(product.getProductId());
            purchaseProductDTO.setQuantity(product.getQuantity());

            PurchaseProduct purchaseProduct = Optional.of(purchaseProductDTO)
                    .map(CreateOrUpdatePurchaseProductDTO::to).get();

            purchaseProducts.add(purchaseProduct);
        }

        purchaseProductService.create(purchaseProducts);

        PurchaseDTO purchaseDTO = Optional.of(purchase)
                .map(PurchaseDTO::from).get();

        return ResponseEntity.ok().body(purchaseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> findAll() {
        List<PurchaseDTO> response = new ArrayList<>();

        List<Purchase> purchases = service.findAll();

        for (Purchase purchase: purchases) {
            PurchaseDTO dto = Optional.of(purchase)
                    .map(PurchaseDTO::from).get();

            response.add(dto);
        }

        return ResponseEntity.ok().body(response);
    }

}
