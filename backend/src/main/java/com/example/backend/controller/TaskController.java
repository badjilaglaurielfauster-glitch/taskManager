package com.example.backend.controller;

import com.example.backend.dto.TaskDto;

import com.example.backend.model.Task;
import com.example.backend.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    //GET /tasks?userId=res
    @GetMapping
    public List<TaskDto> getTask(@RequestParam Long userId) {
        return taskService.getTasksByUser(userId).stream()
                .map(task -> new TaskDto(task.getId(),task.getTitle(),task.getCompleted()))
                .collect(Collectors.toList());

    }


    @PostMapping
    public TaskDto createTask(@RequestParam Long userId, @RequestBody TaskDto taskDto) {

        try {
            Task task = taskService.createTask(userId, taskDto.getTitle());
            return new TaskDto(task.getId(), task.getTitle(), task.getCompleted());
        } catch (Exception e) {
            e.printStackTrace(); // ça s'affichera dans la console IntelliJ
            throw e;
        }

    }

    @PutMapping("/{taskId}/toggle")
    public void toggleTask(@PathVariable("taskId") Long taskId) {
        taskService.toggleTask(taskId);
    }


    @DeleteMapping("/{taskId}/reset")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }




}
