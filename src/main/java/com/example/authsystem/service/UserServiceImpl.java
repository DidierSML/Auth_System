package com.example.authsystem.service;

import com.example.authsystem.dto.UserResponse;
import com.example.authsystem.entity.User;
import com.example.authsystem.exception.NotFoundCustomException;
import com.example.authsystem.repository.UserRepository;
import com.example.authsystem.security.JwtService;
import com.example.authsystem.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getUser(Long id) {

        User existingUser = userRepository.findById(id).
                orElseThrow(()-> new NotFoundCustomException
                        ("User not found"));

        return new UserResponse(
                existingUser.getId(),
                existingUser.getFullName(),
                existingUser.getEmail(),
                existingUser.isActive()
        );
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> existingUsers = userRepository.findAll();
        List<UserResponse> responseList = new ArrayList<>();

        for(User user : existingUsers){

            UserResponse userResponse = new UserResponse
                    (
                            user.getId(),
                            user.getFullName(),
                            user.getEmail(),
                            user.isActive()
                    );

            responseList.add(userResponse);
        }

        return responseList;
    }

}
