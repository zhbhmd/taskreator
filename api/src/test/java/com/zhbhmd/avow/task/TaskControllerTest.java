package com.zhbhmd.avow.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.when;

@WebFluxTest(TaskController.class)
public class TaskControllerTest {

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
    public void testSaveUser() {

        Task task = new Task(null,title, description, status, time, date);

        when(taskService.createTask(task)).thenReturn(Mono.just(task));

        webClient
                .post().uri("/tasks").bodyValue(task)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Task.class);
    }
}
