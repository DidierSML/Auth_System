package com.example.authsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AssignPasswordRequest(

        @Schema(description = "Token de verificación recibido por email",
                example = "d94d8a40-70e3-11ed-9b6a-0242ac120002")
        @NotBlank
        String token,

        @Schema(description = "Nueva contraseña (mín 8 caracteres, 1 mayúscula, 1 número)",
                example = "P@ssw0rd_Segura123",
                minLength = 8)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "Formato de contraseña inválido")
        String newPassword) {
}
