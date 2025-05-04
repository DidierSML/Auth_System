package com.example.authsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @Schema(
                description = "Email registrado",
                example = "maria.garcia@correo.com",
                pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )
        @NotBlank(message = "Email requerido")
        @Email(message = "Formato de email inválido")
        String email,

        @Schema(
                description = "Contraseña",
                example = "MiC0ntr@s3ña_2024",
                minLength = 8
        )
        @NotBlank(message = "Contraseña requerida")
        String password
) {
}
