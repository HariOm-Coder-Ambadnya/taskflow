package com.taskflow.service.impl;

import com.taskflow.dto.request.TaskRequestDTO;
import com.taskflow.dto.request.TaskStatusUpdateDTO;
import com.taskflow.dto.response.TaskResponseDTO;
import com.taskflow.entity.Task;
import com.taskflow.entity.User;
import com.taskflow.exception.ResourceNotFoundException;
import com.taskflow.repository.TaskRepository;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .user(user)
                .build();

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        Task task = getTask(id);
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        return mapToResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponseDTO updateStatus(Long id, TaskStatusUpdateDTO dto) {
        Task task = getTask(id);
        task.setStatus(dto.getStatus());
        return mapToResponse(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(getTask(id));
    }

    @Override
    public TaskResponseDTO getTaskById(Long id) {
        return mapToResponse(getTask(id));
    }

    @Override
    public List<TaskResponseDTO> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    private TaskResponseDTO mapToResponse(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .createdAt(task.getCreatedAt())
                .build();
    }
}
