package com.taskflow.controller;

import com.taskflow.dto.request.UserRequestDTO;
import com.taskflow.dto.response.ApiResponse;
import com.taskflow.dto.response.UserResponseDTO;
import com.taskflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        return new ApiResponse<>("SUCCESS", "User created", userService.createUser(dto));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponseDTO> getUser(@PathVariable Long id) {
        return new ApiResponse<>("SUCCESS", "User fetched", userService.getUserById(id));
    }
}
