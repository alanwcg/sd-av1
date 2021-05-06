package com.example.av1.resource;

import com.example.av1.domain.AppError;
import com.example.av1.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppError> appException(
            AppException e,
            HttpServletRequest request
    ) {
        AppError err = new AppError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad request",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppError> methodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        AppError error = new AppError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                String.format("Required request body field '%s' is missing", e.getFieldError().getField()),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<AppError> httpServerErrorException(
            HttpServerErrorException e,
            HttpServletRequest request
    ) {
        AppError error = new AppError(
                System.currentTimeMillis(),
                HttpStatus.UNAUTHORIZED.value(),
                "Bad Request",
                "Credit card limit is insufficient",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}
