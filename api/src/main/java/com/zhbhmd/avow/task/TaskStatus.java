package com.zhbhmd.avow.task;

public record TaskStatus(String label, String code) {

    public static final TaskStatus TO_DO = new TaskStatus("To Do", "TO_DO" );
    public static final TaskStatus COMPLETED = new TaskStatus("Completed", "COMPLETED");
}
