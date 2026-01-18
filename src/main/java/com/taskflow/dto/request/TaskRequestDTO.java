package com.taskflow.dto.request;

import com.taskflow.entity.TaskPriority;
import com.taskflow.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDTO {
    @NotBlank
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Long userId;
}
