package com.example.av1.resource.purchase.dto;

import com.example.av1.domain.Product;
import com.example.av1.domain.Purchase;
import com.example.av1.domain.PurchaseProduct;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Data
public class CreateOrUpdatePurchaseProductDTO {

    private Long id;

    @NotNull(message = "Puchase id is missing")
    private Long purchaseId;

    @NotNull(message = "Product id is missing")
    private Long ProductId;

    @NotNull(message = "Product quantity is missing")
    private Integer quantity;

    public static PurchaseProduct to(CreateOrUpdatePurchaseProductDTO dto) {
        if(Objects.isNull(dto)) {
            return null;
        }

        PurchaseProduct purchaseProduct = new PurchaseProduct();

        Optional.ofNullable(dto.getPurchaseId())
                .map(id -> Purchase.builder().id(id).build())
                .ifPresent(purchaseProduct::setPurchase);

        Optional.ofNullable(dto.getProductId())
                .map(id -> Product.builder().id(id).build())
                .ifPresent(purchaseProduct::setProduct);

        Optional.ofNullable(dto.getQuantity())
                .ifPresent(purchaseProduct::setQuantity);

        return purchaseProduct;
    }

}
