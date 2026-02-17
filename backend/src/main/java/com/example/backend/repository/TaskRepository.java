package com.example.backend.repository;

import com.example.backend.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;


    private List<Task> getTasks() {
        return tasks;
    }

    public Task save(Task task) {
        task.setId(nextId);
        nextId++;
        tasks.add(task);
        return task;

    }

    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }


    public List<Task> findByUserId(Long userId) {
        List<Task> userTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getUserId().equals(userId)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }


    public void deleteById(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }



}
