package com.example.authsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotFoundCustomException extends RuntimeException {

    private final String message;
}
