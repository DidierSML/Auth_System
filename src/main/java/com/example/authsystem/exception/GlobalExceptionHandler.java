package com.example.authsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundCustomException.class)
    @ResponseBody
    public ErrorResponse handleEmailConflict(NotFoundCustomException ex) {

        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), new Date());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        ex.getBindingResult().getFieldErrors();

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                new Date()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
