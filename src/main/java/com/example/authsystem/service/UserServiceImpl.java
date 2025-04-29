package com.example.authsystem.service;

import com.example.authsystem.dto.UserResponse;
import com.example.authsystem.repository.AuthRepository;
import com.example.authsystem.repository.UserRepository;
import com.example.authsystem.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;


    @Override
    public UserResponse getUser(Long id) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return null;
    }

}
