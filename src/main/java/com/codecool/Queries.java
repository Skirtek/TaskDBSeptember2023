package com.codecool;

public class Queries {
    public static final String ADD_TASK = "INSERT INTO tasks (description, finished) VALUES(?,?)";
    public static final String GET_TASK = "SELECT * FROM tasks WHERE id = ?";
    public static final String UPDATE_TASK = "UPDATE tasks SET description = ?, finished = ? WHERE id = ?";
    public static final String GET_ALL_TASKS = "SELECT * FROM tasks";
    public static final String DELETE_TASK = "DELETE FROM tasks WHERE id = ?";
}
