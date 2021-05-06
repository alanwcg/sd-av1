package com.example.av1.resource.purchase.dto;

import com.example.av1.domain.Purchase;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Data
public class PurchaseDTO implements Serializable {

    private String customer;
    private Double total;
    private Integer installments;

    public static PurchaseDTO from(Purchase purchase) {
        if(Objects.isNull(purchase)) {
            return null;
        }

        PurchaseDTO dto = new PurchaseDTO();

        Optional.ofNullable(purchase.getCustomer())
                .ifPresent(dto::setCustomer);

        Optional.ofNullable(purchase.getTotal())
                .ifPresent(dto::setTotal);

        Optional.ofNullable(purchase.getInstallments())
                .ifPresent(dto::setInstallments);

        return dto;
    }

}
