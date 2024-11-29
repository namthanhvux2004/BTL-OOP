package com.StartUp.StudyW.exception;

import com.StartUp.StudyW.dto.request.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception exception) {
        // Log the exception for debugging purposes
        logger.error("Unhandled exception: ", exception);

        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.UNCATEGORIED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIED_EXCEPTION.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse); // Change to 500 if it's a general exception
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        // Log the exception for debugging purposes
        logger.error("AppException: ", exception);

        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse); // Return 400 (Bad Request) for AppException
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError() != null ? exception.getFieldError().getDefaultMessage() : "INVALID_KEY";

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {
            // Log the error and fall back to default ErrorCode
            logger.warn("Invalid error code: {}", enumKey, e);
        }

        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse); // Return 400 (Bad Request) for validation errors
    }
}
