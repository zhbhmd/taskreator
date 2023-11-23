package com.zhbhmd.avow.task;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("tasks")
public class Task {

    @Id
    private Integer id;

    private String title;

    private String description;

    private String status;

    private LocalTime time;

    private LocalDate date;
}
