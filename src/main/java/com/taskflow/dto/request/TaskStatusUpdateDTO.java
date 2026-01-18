package com.taskflow.dto.request;

import com.taskflow.entity.TaskStatus;
import lombok.Data;

@Data
public class TaskStatusUpdateDTO {
    private TaskStatus status;
}
