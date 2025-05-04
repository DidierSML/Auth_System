package com.example.authsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponse(

        @Schema(
                description = "ID único del usuario",
                example = "2"
        )
        Long id,

        @Schema(
                description = "Nombre del usuario",
                example = "Maria Lopez"
        )
        String fullName,

        @Schema(
                description = "Email registrado",
                example = "usuario@dominio.com"
        )
        String email,

        @Schema(
                description = "Estado de activación del usuario",
                example = "true"
        )
        boolean active
) {
}
