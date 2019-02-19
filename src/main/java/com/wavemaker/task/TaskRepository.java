package com.wavemaker.task;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);

    List<Task> getTasks();

    Task getTaskById(String id);

    boolean updateTask(Task task);

    boolean deleteTaskById(String id);
}
