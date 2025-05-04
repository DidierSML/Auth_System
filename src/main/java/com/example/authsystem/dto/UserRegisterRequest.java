package com.example.authsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(

        @Schema(
                description = "Nombre completo del usuario (mínimo 3 caracteres)",
                example = "Carlos Sánchez",
                minLength = 3,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "El nombre no puede estar vacío")
        String fullName,


        @Schema(
                description = "Correo electrónico válido",
                example = "maria.garcia@correo.com",
                pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "Formato de email inválido")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "Formato de email inválido. Ejemplo válido: usuario@dominio.com"
        )
        @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
        String email
) {
}
