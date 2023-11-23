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

    public Mono<Task> markTaskDoneById(String taskId){
        return taskRepository.findById(Integer.parseInt(taskId))
                .flatMap(dbTask -> {
                    dbTask.setStatus(TaskStatus.DONE.code());
                    return taskRepository.save(dbTask);
                });
    }
}

