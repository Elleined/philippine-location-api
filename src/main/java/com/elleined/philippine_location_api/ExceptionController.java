package com.elleined.philippine_location_api;

import com.elleined.philippine_location_api.exception.PhilippineLocationAPIException;
import com.elleined.philippine_location_api.exception.resource.ResourceException;
import com.elleined.philippine_location_api.exception.resource.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<APIResponse> handleResourceException(ResourceException ex) {
        var responseMessage = new APIResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        var responseMessage = new APIResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhilippineLocationAPIException.class)
    public ResponseEntity<APIResponse> handlePhilippineLocationAPIException(PhilippineLocationAPIException ex) {
        var responseMessage = new APIResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<APIResponse>> handleBindException(BindException ex) {
        List<APIResponse> errors = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .map(errorMessage -> new APIResponse(errorMessage, HttpStatus.BAD_REQUEST))
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
