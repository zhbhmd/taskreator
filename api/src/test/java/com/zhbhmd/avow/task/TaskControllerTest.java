package com.zhbhmd.avow.task;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebFluxTest(TaskController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskControllerTest {

    private final Integer id = 1;
    private final String title = "Task Repo Title";
    private final String description = "Task Repo Description";
    private final String status = TaskStatus.TO_DO.code();
    private final LocalTime time = LocalTime.now();
    private final LocalDate date = LocalDate.now();

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TaskService taskService;

    @Test
    @Order(1)
    public void testSaveTask() {

        Task task = new Task(id,title, description, status, time, date);

        when(taskService.createTask(task)).thenReturn(Mono.just(task));

        webClient
                .post().uri("/tasks").bodyValue(task)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Task.class)
                .consumeWith(t -> {
                    assertNotNull(t.getResponseBody().getId());
                    assertEquals(title, t.getResponseBody().getTitle());
                });
    }

    @Test
    @Order(2)
    public void testGetAll() {

        Task task = new Task(id,title, description, status, time, date);

        when(taskService.createTask(task)).thenReturn(Mono.just(task));

        webClient
                .get().uri("/tasks")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Flux.class);
    }

    @Test
    @Order(3)
    public void testMarkAsDone() {

        Task task = new Task(id,title, description, TaskStatus.DONE.code(), time, date);

        when(taskService.markTaskDoneById(id.toString())).thenReturn(Mono.just(task));

        webClient
                .put().uri("/tasks/markDone/"+id.toString())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Task.class)
                .consumeWith(t -> {
                    assertNotNull(t.getResponseBody().getId());
                    assertEquals(title, t.getResponseBody().getTitle());
                    assertEquals(TaskStatus.DONE.code(), t.getResponseBody().getStatus());
                });;
    }
}
