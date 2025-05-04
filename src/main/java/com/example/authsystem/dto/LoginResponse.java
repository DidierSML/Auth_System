package com.example.authsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(

        @Schema(
                description = "Tiempo de expiración en segundos",
                example = "3600" // 1 hora
        )
        String jwtToken
) {
}
