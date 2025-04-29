package com.example.authsystem.controller;

import com.example.authsystem.dto.*;
import com.example.authsystem.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public String register (@RequestBody UserRegisterRequest registerRequest){

        authService.registerUser(registerRequest);
        return "User has been registered successfully. Please check your email inbox";
    }

//    @GetMapping("/verify")
//    @ResponseStatus(HttpStatus.OK)
//    public String verifyAccount (@RequestParam String token){
//
//        sessionService.verifyAccount(token);
//        return "Credentials has been verified successfully";
//    }

//    @PostMapping("/confirm-password")
//    @ResponseStatus(HttpStatus.OK)
//    public String assignPassword (String token, String newPassword){
//        sessionService.assignPassword(token, newPassword);
//        return "Password has been assigned successfully";
//    }

    @PostMapping("/assign-password")
    @ResponseStatus(HttpStatus.OK)
    public String assignPassword (@RequestBody AssignPasswordRequest request){
        authService.assignPassword(request);
        return "The new password has been assigned to the user ";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login (@RequestBody LoginRequest request){
       return authService.login(request);
    }

    @PostMapping("/forgot-password")
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword (@RequestBody ForgotPasswordRequest request){
        authService.forgotPassword(request);

    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)
    public void recoverPassword (@RequestBody ResetPasswordRequest request){
        authService.resetPassword(request);

    }
}
