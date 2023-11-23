package com.zhbhmd.avow.task;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    private final Integer id = 1;
    private final String title = "Task Repo Title";
    private final String description = "Task Repo Description";
    private final String status = TaskStatus.TO_DO.code();
    private final LocalTime time = LocalTime.now();
    private final LocalDate date = LocalDate.now();

    @Test
    public void testCreateTask() {
        Task task = new Task(id,title, description, status, time, date);
        when(taskRepository.save(task)).thenReturn(Mono.just(task));
        Mono<Task> taskMono = taskService.createTask(task);
        StepVerifier
                .create(taskMono)
                .consumeNextWith(t ->{
                    assertNotNull(t.getId());
                    assertEquals(title, t.getTitle());
                    assertEquals(description, t.getDescription());
                    assertEquals(status, t.getStatus());
                    assertEquals(time.truncatedTo(ChronoUnit.MINUTES), t.getTime().truncatedTo(ChronoUnit.MINUTES));
                    assertEquals(date, t.getDate());
                        }

                )
                .verifyComplete();
    }

    @Test
    public void testFindAllReturnsOneWhenOneInserted() {
        Task task = new Task(id,title, description, status, time, date);
        when(taskRepository.findAll()).thenReturn(Flux.just(task));
        Flux<Task> taskFlux = taskService.findAll();
        StepVerifier
                .create(taskFlux).expectNextCount(1).verifyComplete();
    }

    @Test
    public void testTaskDoneWhenMarkedAsDone() {
        Task task = new Task(id,title, description, status, time, date);
        when(taskRepository.findById(id)).thenReturn(Mono.just(task));
        Task doneTask = new Task(id,title, description, TaskStatus.DONE.code(), time, date);
        when(taskRepository.save(task)).thenReturn(Mono.just(doneTask));
        Mono<Task> taskFlux = taskService.markTaskDoneById(id.toString());
        StepVerifier
                .create(taskFlux).consumeNextWith(t -> {
                    assertNotNull(t.getId());
                    assertEquals(TaskStatus.DONE.code(), t.getStatus());
        }).verifyComplete();
    }
}
