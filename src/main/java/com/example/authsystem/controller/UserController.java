package com.example.authsystem.controller;

import com.example.authsystem.dto.UserResponse;
import com.example.authsystem.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Users", description = "User management Operations")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Obtener usuario por ID",
            description = """
                "Requiere autenticación JWT. Acceso: ADMIN o propio usuario"
                """
            )
    @ApiResponse(responseCode = "200", description = "OK - Lista de usuarios (puede estar vacía)")
    @ApiResponse(responseCode = "401", description = "Token inválido")
    public UserResponse register (@PathVariable (value = "id") Long id){

        return userService.getUser(id);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Obtener todos los usuarios",
            description = """
                "Requiere autenticación JWT. Acceso: ADMIN o propio usuario"
                """
    )
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "401", description = "Token inválido")
    public List<UserResponse> getAllUsers (){

        return userService.getAllUsers();
    }

}

