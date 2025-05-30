package com.example.authsystem.service;

import com.example.authsystem.dto.*;
import com.example.authsystem.entity.Token;
import com.example.authsystem.entity.User;
import com.example.authsystem.exception.NotFoundCustomException;
import com.example.authsystem.repository.AuthRepository;
import com.example.authsystem.repository.UserRepository;
import com.example.authsystem.security.JwtService;
import com.example.authsystem.service.interfaces.AuthService;
import com.example.authsystem.util.TokenType;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registerUser(UserRegisterRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new NotFoundCustomException("This user is already registered");
        }

        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setActive(false);

        userRepository.save(user);

        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setType(TokenType.VERIFICATION);
        token.setCreationDate(LocalDateTime.now());
        token.setExpirationDate(LocalDateTime.now().plusHours(24));
        token.setUsed(false);
        token.setUser(user);

        authRepository.save(token);

        emailService.sendEmail(
                user.getEmail(),
                "Cuenta Registrada!",
                "Hi " + user.getFullName() + ",\n\n" +
                        "Para completar tu registro, haz clic en el siguiente enlace para asignar tu contraseña:\n" +
                        "http://localhost:8080/api/auth/assign-password?token= " + token.getToken() + "\n\n" +
                        "¡Saludos!"
        );
    }

    @Transactional
    @Override
    public void assignPassword(AssignPasswordRequest request) {

        Token token = authRepository.findByToken(request.token())
                .orElseThrow(() -> new NotFoundCustomException("Invalid Token"));

        if (token.isUsed() || token.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new NotFoundCustomException("Invalid or Expired Token");
        }

        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        user.setActive(true);
        token.setUsed(true);

        userRepository.save(user);
        authRepository.save(token);

        emailService.sendEmail(
                user.getEmail(),
                "Contraseña Asignada!",
                "Hola " + user.getFullName() + ",\n\nTu contraseña ha sido asignada exitosamente." +
                        "\n\nSi no realizaste este cambio, por favor contacta con soporte." +
                        "¡Saludos!"
        );
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new NotFoundCustomException("User not found"));

        // Verificar si la contraseña es correcta usando el passwordEncoder
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new NotFoundCustomException("Incorrect Password");
        }

        if (!user.isActive()) {
            throw new NotFoundCustomException("Inactive user");
        }

        String jwt = jwtService.generateToken(user.getEmail());//Mediante Email
        return new LoginResponse(jwt);
    }

    @Transactional
    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundCustomException("User not found"));

        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setType(TokenType.RECUPERATION);
        token.setCreationDate(LocalDateTime.now());
        token.setExpirationDate(LocalDateTime.now().plusHours(1));
        token.setUsed(false);
        token.setUser(user);

        authRepository.save(token);

        emailService.sendEmail(
                user.getEmail(),
                "Olvidaste tu Contraseña?",
                "Hola " + user.getFullName() + ",\n\nRecibimos una solicitud para recuperar tu contraseña. \n" +
                        "Si fuiste tú, haz clic en el siguiente enlace para crear una nueva: " +
                        "http://localhost:8080/api/auth/reset-password?token= " + token.getToken() +
                        "\n\nSi no realizaste este cambio, ignoralo, o por favor contacta con soporte." +
                        "¡Saludos!"
        );
    }

    @Transactional
    @Override
    public void resetPassword(ResetPasswordRequest request) {

        Token token = authRepository.findByToken(request.token())
                .orElseThrow(() -> new NotFoundCustomException("Invalid Token"));

        if (token.isUsed() || token.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new NotFoundCustomException("Invalid or Expired Token");
        }

        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        token.setUsed(true);

        userRepository.save(user);
        authRepository.save(token);

        emailService.sendEmail(
                user.getEmail(),
                "Contraseña Actualizada!",
                "Hola " + user.getFullName() + ",\n\nTu contraseña ha sido actualizada exitosamente." +
                        "\n\nSi no realizaste este cambio, por favor contacta con soporte." +
                        "¡Saludos!"
        );
    }
}