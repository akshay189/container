package com.wavemaker.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTasksRepository implements TaskRepository {

    private Map<String, Task> tasks = new HashMap<>();

    @Override
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task getTaskById(String id) {
        return tasks.get(id);
    }

    @Override
    public boolean updateTask(Task task) {
        if (deleteTaskById(task.getId())) {
            tasks.put(task.getId(), task);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTaskById(String id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        }
        return false;
    }
}
