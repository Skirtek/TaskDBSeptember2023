package com.codecool;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private TaskDao taskDao;

    @Override
    public void update(Task task) {
        if(task.getDescription() == null || task.getDescription().length() == 0){
            return;
        }

        taskDao.updateTask(task);
    }
}
