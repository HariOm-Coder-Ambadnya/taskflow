package com.taskflow.service.impl;

import com.taskflow.dto.request.UserRequestDTO;
import com.taskflow.dto.response.UserResponseDTO;
import com.taskflow.entity.User;
import com.taskflow.exception.ResourceNotFoundException;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        User saved = userRepository.save(user);
        return UserResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .build();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
