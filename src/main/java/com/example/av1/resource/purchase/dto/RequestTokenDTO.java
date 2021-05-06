package com.example.av1.resource.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTokenDTO {

    private String number;

    private LocalDate dueDate;

    private String cvn;

}
