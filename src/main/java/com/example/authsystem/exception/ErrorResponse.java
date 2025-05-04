package com.example.authsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus statusCode;
    private String statusMsg;
    private Date timestamp;

}
