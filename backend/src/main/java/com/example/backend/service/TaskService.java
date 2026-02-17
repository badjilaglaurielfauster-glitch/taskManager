package com.example.backend.service;

import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {



        private final TaskRepository taskRepository;

        public TaskService(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }


        public Task createTask(Long userId, String title){

            if(title == null || title.trim().isEmpty()){
                throw new IllegalArgumentException("Title cannot be empty");
            }

            Task task = new Task(title, userId);
            return taskRepository.save(task);

        }


        public List<Task> getTasksByUser(Long userId){
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
