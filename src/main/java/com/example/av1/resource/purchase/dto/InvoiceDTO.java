package com.example.av1.resource.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private String creditCardToken;

    private Double value;

    private Integer installments;

    private String establishment;

}
