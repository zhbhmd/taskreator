package com.zhbhmd.avow.task;

public record Status(String label, String code) {

    public static final Status TO_DO = new Status("To Do", "TO_DO" );
    public static final Status COMPLETED = new Status("Completed", "COMPLETED");
}
