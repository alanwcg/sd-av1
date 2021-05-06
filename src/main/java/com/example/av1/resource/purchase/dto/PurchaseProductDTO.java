package com.example.av1.resource.purchase.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PurchaseProductDTO {

    @NotNull(message = "Product id is missing")
    private Long productId;

    @NotNull(message = "Product quantity is missing")
    private Integer quantity;

}
