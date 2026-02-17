package com.example.backend.service;

import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TacheService {

    private final TaskRepository taskRepository;

    public TacheService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task createTask(Long userId, String title){

        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }

        Task task = new Task(title, userId);
        return taskRepository.save(task);

    }


    public List<Task> getTasksBuUser(Long userId){
        return taskRepository.findByUserId(userId);

    }



    public void toggleTask( Long taskId){
        taskRepository.findById(taskId)
                .ifPresent(Task::toggleCompleted);
    }


    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    }




}
