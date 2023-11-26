package com.zhbhmd.avow.task;

import com.zhbhmd.avow.exception.TaskNotFoundException;
import com.zhbhmd.avow.exception.TaskNotSavedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TaskService  {

    private final TaskRepository taskRepository;
    public Mono<Task> createTask(Task task){

        return taskRepository
                .save(task)
                .onErrorResume(e -> Mono.error(new TaskNotSavedException(
                        "Task not saved", e)));
    }

    public Flux<Task> findAll() {
        return taskRepository.findAll();
    }

    public Mono<Task> getTaskById(String id) {
        return taskRepository.findById(Integer.parseInt(id)).switchIfEmpty(Mono.error(new TaskNotFoundException("Task not found")));
    }

    public Mono<Task> markTaskDoneById(String taskId){
        return taskRepository.findById(Integer.parseInt(taskId)).
                switchIfEmpty(Mono.error(new TaskNotFoundException(
                        "Task not found")))
                .flatMap(dbTask -> {
                    dbTask.setStatus(TaskStatus.DONE.code());
                    return taskRepository.save(dbTask);
                });
    }
}

