package com.saksoft.exception;

import com.saksoft.helper.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

        String message = ex.getMessage();
        ApiResponse api = new ApiResponse(message, false);
        api.setMessage(message);
        return new ResponseEntity<ApiResponse>(api, HttpStatus.BAD_REQUEST);

    }


}
