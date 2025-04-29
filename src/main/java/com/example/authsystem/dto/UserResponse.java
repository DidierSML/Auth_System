package com.example.authsystem.dto;

public record UserResponse(
        String fullName,
        String email,
        boolean active
) {
}
