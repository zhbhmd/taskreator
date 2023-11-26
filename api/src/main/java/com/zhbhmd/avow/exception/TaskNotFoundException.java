package com.zhbhmd.avow.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String errorMessage, Throwable err) {

        super(errorMessage, err);
    }

    public TaskNotFoundException(String errorMessage) {

        super(errorMessage);
    }
}
