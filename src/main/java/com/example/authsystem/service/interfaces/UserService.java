package com.example.authsystem.service.interfaces;

import com.example.authsystem.dto.UserRegisterRequest;
import com.example.authsystem.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUser (Long id);
    List<UserResponse> getAllUsers ();


}
