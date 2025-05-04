package com.example.authsystem.controller;

import com.example.authsystem.dto.*;
import com.example.authsystem.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and recuperation Operations")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Iniciar registro de un usuario",
            description = """
                Paso inicial del registro: Valida el email y envía un link de confirmación via email.
                Requisitos:
                - Email debe ser único y válido
                - El link expira en 24 horas
                """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = @ExampleObject(
                            value = """
                    {
                        "email": "usuario@dominio.com",
                        "fullName": "María García"
                    }"""
                    ))
            ))
    @ApiResponse(responseCode = "201", description = "Email de confirmación enviado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "409", description = "Email ya registrado")
    public String register (@Valid @RequestBody UserRegisterRequest registerRequest) {
        authService.registerUser(registerRequest);
        return "User registered successfully. Please verify your email to continue";
    }

    @PostMapping("/assign-password")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Completar registro con contraseña",
            description = """
                Paso final del registro: Asignar contraseña usando token de verificación recibido.
                Requisitos:
                - Token debe ser válido y no expirado (24h de vigencia)
                - Contraseña debe cumplir política de seguridad
                """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = @ExampleObject(
                            name = "Ejemplo con contraseña válida",
                            value = """
                {
                    "token": "a5597252-fb25-4184-86d2-60029214bb5d",
                    "newPassword": "P@ssw0rd_Segura123"
                }"""
                    ))
            )
    )
    @ApiResponse(responseCode = "201", description = "Contraseña asignada - Usuario activo")
    @ApiResponse(responseCode = "400", description = "Error en formato de contraseña")
    @ApiResponse(responseCode = "404", description = "Token inválido o no encontrado")
    @ApiResponse(responseCode = "410", description = "Token expirado")
    public String assignPassword (@Valid @RequestBody AssignPasswordRequest request){

        authService.assignPassword(request);
        return "The new password has been assigned to the user ";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Iniciar sesión",
            description = """
                Paso inicial del registro: Autentica un usuario usando credenciales válidas y retorna un token JWT.
                Requisitos:
                - Email debe ser válido
                - Contraseña debe de ser válida
                """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = @ExampleObject(
                            value = """
                    {
                        "email": "usuario@dominio.com",
                        "password": "P@ssw0rd_123"
                    }"""
                    ))
            ))
    @ApiResponse(responseCode = "201", description = "Autenticación exitosa")
    @ApiResponse(responseCode = "400", description = "Formato de email o contraseña inválido")
    @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    public LoginResponse login (@Valid @RequestBody LoginRequest request){

        return authService.login(request);
    }

    @PostMapping("/forgot-password")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Solicitud recuperación de contraseña",
            description = """
                Paso inicial del proceso: Envía un email con un enlace temporal para restablecer la contraseña.
                Requisitos:
                - El enlace expira en 1 hora
                - Solo válido para cuentas verificadas
                """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = @ExampleObject(
                            value = """
                    {
                        "email": "usuario@dominio.com"
                    }"""
                    ))
            ))
    @ApiResponse(responseCode = "202", description = "Solicitud aceptada (si el email existe, se enviará un enlace)")
    @ApiResponse(responseCode = "400", description = "Formato de email inválido")
    public String forgotPassword (@Valid @RequestBody ForgotPasswordRequest request){
        authService.forgotPassword(request);
        return "An email has been sent to you to reset your password.";

    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)

    @Operation(
            summary = "Restablecer contraseña",
            description = """
                Paso final del proceso: Valida el token y actualiza la contraseña.
                Requisitos:
                - El token debe ser válido y no expirado
                - La contraseña debe cumplir la política de seguridad
                """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = @ExampleObject(
                            value = """
                                    {
                                        "token": "a5597252-fb25-4184-86d2-60029214bb5d",
                                        "newPassword": "NuevaC0ntr@s3ña_2024"
                                    }"""
                    ))
            ))
    @ApiResponse(responseCode = "202", description = "Solicitud aceptada (si el email existe, se enviará un enlace)")
    @ApiResponse(responseCode = "400", description = "Formato de email inválido")
    public String resetPassword (@Valid @RequestBody ResetPasswordRequest request){

        authService.resetPassword(request);
        return "Your password has been updated successfully.";

    }
}
