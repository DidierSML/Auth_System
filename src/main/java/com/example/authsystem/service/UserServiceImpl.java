package com.example.authsystem.service;

import com.example.authsystem.dto.UserResponse;
import com.example.authsystem.entity.User;
import com.example.authsystem.repository.UserRepository;
import com.example.authsystem.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getUser(Long id) {

        User existingUser = userRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException
                        ("El usuario con " + id + " no existe en nuestro sistema"));

        return new UserResponse(
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
                            user.getFullName(),
                            user.getEmail(),
                            user.isActive()
                    );

            responseList.add(userResponse);
        }

        return responseList;
    }

}
