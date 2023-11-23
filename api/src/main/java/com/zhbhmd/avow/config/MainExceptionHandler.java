package com.zhbhmd.avow.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.ResponseEntity.internalServerError;

@RestControllerAdvice
@Slf4j
public class MainExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity lastHandler(Exception ex) {

        log.debug("An error occurred. Please check logs. " + ex);
        return internalServerError().build();
    }
}
