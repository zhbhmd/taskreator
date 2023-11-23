package com.zhbhmd.avow.task;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    private final String title = "Task Repo Title";
    private final String description = "Task Repo Decription";
    private final String status = TaskStatus.TO_DO.code();
    private final LocalTime time = LocalTime.now();
    private final LocalDate date = LocalDate.now();

    @Test
    public void testTaskRepositoryExisted() {
        assertNotNull(taskRepository);
    }

    @Test
    public void testInsertAndQueryAll() {
        Task task = new Task(null,title, description, status, time, date);

        this.taskRepository.save(task)
                .then().block(Duration.ofSeconds(5));

        this.taskRepository.findAll()
                .take(1)
                .as(StepVerifier::create)
                .consumeNextWith(t -> {
                    assertNotNull(t.getId());
                    assertEquals(title, t.getTitle());
                    assertEquals(description, t.getDescription());
                    assertEquals(status, t.getStatus());
                    assertEquals(time.truncatedTo(ChronoUnit.MINUTES), t.getTime().truncatedTo(ChronoUnit.MINUTES));
                    assertEquals(date, t.getDate());
                })
                .verifyComplete();

    }
}
