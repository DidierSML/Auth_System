package com.example.authsystem.controller;

import com.example.authsystem.dto.UserResponse;
import com.example.authsystem.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User management Operations")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserResponse> getAllUsers (){

        return userService.getAllUsers();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register (Long id){

        return userService.getUser(id);

    }


}

