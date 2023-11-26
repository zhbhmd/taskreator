package com.zhbhmd.avow.exception;

public class TaskNotSavedException extends RuntimeException{

    public TaskNotSavedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
