package com.wavemaker.task;

import com.wavemaker.annotations.PathVariable;
import com.wavemaker.annotations.RequestBody;
import com.wavemaker.framework.RequestMethod;
import com.wavemaker.annotations.Control;
import com.wavemaker.annotations.Request;
import com.wavemaker.framework.exceptions.CustomException;


import java.util.UUID;
import java.util.List;


@Control
@Request(path = "/tasks")
public class TaskService {

    private TaskRepository taskRepository = RepositoryFactory.getRepositoryType(RepositoryType.InMemory);

    @Request(path = "/", method = RequestMethod.GET)
    public List<Task> get() {
        return (taskRepository.getTasks());
    }

    @Request(path = "/{id}", method = RequestMethod.GET)
    public Task getById(@PathVariable("id") String id) {
        return (taskRepository.getTaskById(id));
    }

    @Request(path = "/", method = RequestMethod.POST)
    public Task post(@RequestBody Task object) {
        if (object != null) {
            object.setId(UUID.randomUUID().toString());
            taskRepository.addTask(object);
            return getById(object.getId());
        }
        return null;
    }

    @Request(path = "/{id}", method = RequestMethod.PUT)
    public boolean put(@PathVariable("id") String id, @RequestBody Task task) throws CustomException {
        if (task != null) {
            if (id.equals(task.getId())) {
                taskRepository.updateTask(task);
                return true;
            } else {
                throw new CustomException("Id doesn't match");
            }
        } else {
            throw new CustomException("Request body is empty");
        }
    }

    @Request(path = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") String id) throws CustomException {
        if (taskRepository.deleteTaskById(id)) {
            return true;
        } else {
            throw new CustomException("Invalid ID");
        }
    }
}
