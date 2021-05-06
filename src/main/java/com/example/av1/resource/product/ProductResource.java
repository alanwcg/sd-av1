package com.example.av1.resource.product;

import com.example.av1.domain.Product;
import com.example.av1.resource.product.dto.CreateOrUpdateProductDTO;
import com.example.av1.resource.product.dto.ProductDTO;
import com.example.av1.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductResource {

    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody CreateOrUpdateProductDTO dto) {
        ProductDTO productDTO = Optional.of(dto)
                .map(CreateOrUpdateProductDTO::to)
                .map(service::create)
                .map(ProductDTO::from).get();

        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> response = new ArrayList<>();

        List<Product> products = service.findAll();

        for (Product product : products) {
            ProductDTO dto = Optional.of(product)
                    .map(ProductDTO::from).get();

            response.add(dto);
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Product product = service.findById(id);

        ProductDTO dto = Optional.of(product)
                .map(ProductDTO::from).get();

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(
            @Valid @RequestBody CreateOrUpdateProductDTO dto,
            @PathVariable Long id
    ) {
        dto.setId(id);

        ProductDTO productDTO = Optional.of(dto)
                .map(CreateOrUpdateProductDTO::to)
                .map(service::update)
                .map(ProductDTO::from).get();

        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
