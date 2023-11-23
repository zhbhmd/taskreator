package com.zhbhmd.avow.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TaskService  {

    private final TaskRepository taskRepository;
    public Mono<Task> createTask(Task task){
        return taskRepository.save(task);
    }

    public Flux<Task> findAll() {
        return taskRepository.findAll();
    }
}

