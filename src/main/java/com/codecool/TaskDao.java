package com.codecool;

import java.util.List;

public interface TaskDao {
    long addTask(Task task);

    Task getTask(long id);

    List<Task> getAll();

    void updateTask(Task task);

    void deleteTask(long id);
}
