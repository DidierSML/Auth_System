package com.example.authsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(

        @Schema(
                description = "Email registrado en el sistema",
                example = "ana.rodriguez@correo.com",
                pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )
        @NotBlank(message = "El email es requerido")
        @Email(message = "Formato de email inválido")
        String email
) {
}
