package com.example.authsystem.dto;

public record AssignPasswordRequest(
        String token,
        String newPassword) {
}
