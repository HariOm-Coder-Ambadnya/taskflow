package com.taskflow.service;

import com.taskflow.dto.request.UserRequestDTO;
import com.taskflow.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO getUserById(Long id);
}
