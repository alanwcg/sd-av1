package com.example.av1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppError {

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
