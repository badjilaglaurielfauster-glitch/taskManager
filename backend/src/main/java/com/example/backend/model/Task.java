package com.example.backend.model;

public class Task {

    private Long id;
    private String title;
    private Boolean completed;
    private Long userId;

    public Task() {
    }

    public Task(String title, Long userId) {
        this.title = title;
        this.userId = userId;
        this.completed = false;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    public boolean isValidTitle(){
        return title != null && title.length() > 0;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", userId=" + userId +
                '}';
    }

    public boolean belongsToUser(Long user){
        return this.userId.equals(user);
    }




}
