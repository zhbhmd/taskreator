package com.zhbhmd.avow.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskNotSavedException.class)
    ResponseEntity<ExceptionResponse> notSaveHandler(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse("1001", "An error occurred", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    ResponseEntity<ExceptionResponse> notFoundHandler(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse("1002", "An error occurred", ex.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionResponse> lastHandler(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse("1111", "An error occurred", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
