package com.example.authsystem.dto;

public record UserRegisterRequest(
        String fullName,
        String email
) {
}
