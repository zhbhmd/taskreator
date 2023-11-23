package com.zhbhmd.avow.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin()
@RestController()
@RequestMapping(value = "/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping()
    public Mono<Task> create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<Task> findAll() {
        return taskService.findAll();
    }

    @PutMapping("/markDone/{taskId}")
    public Mono<ResponseEntity<Task>> markTaskDoneById(@PathVariable String taskId){
        return taskService.markTaskDoneById(taskId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
