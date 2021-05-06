package com.example.av1.resource.creditCard;

import lombok.Data;

@Data
public class CreditCardDTO {

    private String number;

    private String owner;

    private String dueDate;

    private String cvn;

    private Double limit;

}
