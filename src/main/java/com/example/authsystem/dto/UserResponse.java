package com.example.authsystem.dto;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        boolean active
) {
}
