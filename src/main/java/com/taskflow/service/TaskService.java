package com.taskflow.service;

import com.taskflow.dto.request.TaskRequestDTO;
import com.taskflow.dto.request.TaskStatusUpdateDTO;
import com.taskflow.dto.response.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO dto);
    TaskResponseDTO updateTask(Long id, TaskRequestDTO dto);
    TaskResponseDTO updateStatus(Long id, TaskStatusUpdateDTO dto);
    void deleteTask(Long id);
    TaskResponseDTO getTaskById(Long id);
    List<TaskResponseDTO> getTasksByUser(Long userId);
}
