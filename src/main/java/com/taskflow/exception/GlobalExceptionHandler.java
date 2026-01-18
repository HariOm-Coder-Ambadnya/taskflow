package com.taskflow.exception;

import com.taskflow.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleNotFound(ResourceNotFoundException ex) {
        return new ApiResponse<>("ERROR", ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleGeneric(Exception ex) {
        return new ApiResponse<>("ERROR", ex.getMessage(), null);
    }
}
