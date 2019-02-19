package com.wavemaker.task;

public class RepositoryFactory {
    public static TaskRepository getRepositoryType(RepositoryType repositoryType) {
        if (repositoryType == RepositoryType.InMemory) {
            return new InMemoryTasksRepository();
        }else{
            return null;
        }
    }
}
