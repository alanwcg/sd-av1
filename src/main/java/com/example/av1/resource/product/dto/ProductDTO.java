package com.example.av1.resource.product.dto;

import com.example.av1.domain.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Data
public class ProductDTO implements Serializable {

    private String name;
    private Double price;

    public static ProductDTO from(Product product) {
        if(Objects.isNull(product)) {
            return null;
        }

        ProductDTO dto = new ProductDTO();

        Optional.ofNullable(product.getName())
                .ifPresent(dto::setName);

        Optional.ofNullable(product.getPrice())
                .ifPresent(dto::setPrice);

        return dto;
    }

}
