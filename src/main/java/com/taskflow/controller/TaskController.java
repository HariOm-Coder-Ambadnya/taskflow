package com.taskflow.controller;

import com.taskflow.dto.request.TaskRequestDTO;
import com.taskflow.dto.request.TaskStatusUpdateDTO;
import com.taskflow.dto.response.ApiResponse;
import com.taskflow.dto.response.TaskResponseDTO;
import com.taskflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ApiResponse<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto) {
        return new ApiResponse<>("SUCCESS", "Task created", taskService.createTask(dto));
    }

    @GetMapping("/{id}")
    public ApiResponse<TaskResponseDTO> getTask(@PathVariable Long id) {
        return new ApiResponse<>("SUCCESS", "Task fetched", taskService.getTaskById(id));
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<TaskResponseDTO>> getTasksByUser(@PathVariable Long userId) {
        return new ApiResponse<>("SUCCESS", "Tasks fetched", taskService.getTasksByUser(userId));
    }

    @PutMapping("/{id}")
    public ApiResponse<TaskResponseDTO> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO dto) {
        return new ApiResponse<>("SUCCESS", "Task updated", taskService.updateTask(id, dto));
    }

    @PutMapping("/{id}/status")
    public ApiResponse<TaskResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody TaskStatusUpdateDTO dto) {
        return new ApiResponse<>("SUCCESS", "Status updated", taskService.updateStatus(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ApiResponse<>("SUCCESS", "Task deleted", null);
    }
}
