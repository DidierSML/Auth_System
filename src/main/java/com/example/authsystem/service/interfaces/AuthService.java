package com.example.authsystem.service.interfaces;

import com.example.authsystem.dto.*;

public interface AuthService {

    void registerUser (UserRegisterRequest request);
    void assignPassword(AssignPasswordRequest request);
    LoginResponse login (LoginRequest loginRequest);
    void forgotPassword (ForgotPasswordRequest request);
    void resetPassword (ResetPasswordRequest request);


}
