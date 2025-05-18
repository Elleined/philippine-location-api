package com.elleined.philippine_location_api;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record APIResponse(String message,
                          HttpStatus status,
                          LocalDateTime timeStamp) {

    APIResponse(String message, HttpStatus status) {
        this(message, status, LocalDateTime.now());
    }
}
