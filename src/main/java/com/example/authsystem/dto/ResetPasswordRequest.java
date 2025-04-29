package com.example.authsystem.dto;

public record ResetPasswordRequest(
        String token,
        String newPassword
) {
}
