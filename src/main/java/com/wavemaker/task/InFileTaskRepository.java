package com.wavemaker.task;

import java.util.List;

public class InFileTaskRepository implements TaskRepository {
    @Override
    public void addTask(Task task) {

    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public Task getTaskById(String id) {
        return null;
    }

    @Override
    public boolean updateTask(Task task) {
        return false;
    }

    @Override
    public boolean deleteTaskById(String id) {
        return false;
    }
}
