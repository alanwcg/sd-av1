package com.example.av1.resource.product.dto;

import com.example.av1.domain.Product;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Data
public class CreateOrUpdateProductDTO {

    private Long id;

    @NotBlank(message = "Product name is missing")
    @Length(max = 100, message = "Product name is invalid")
    private String name;

    @NotNull(message = "Product price is missing")
    private Double price;

    public static Product to(CreateOrUpdateProductDTO dto) {
        if(Objects.isNull(dto)) {
            return null;
        }

        Product product = new Product();

        Optional.ofNullable(dto.getId())
                .ifPresent(product::setId);

        Optional.ofNullable(dto.getName())
                .ifPresent(product::setName);

        Optional.ofNullable(dto.getPrice())
                .ifPresent(product::setPrice);

        return product;
    }

}
