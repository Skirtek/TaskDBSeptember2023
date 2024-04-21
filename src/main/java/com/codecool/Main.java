package com.codecool;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/");
        dataSource.setUser("postgres");
        dataSource.setPassword("@W#E$R%T^Y7u8i");
        dataSource.setDatabaseName("todo_sunday");

        try (Connection connection = dataSource.getConnection()) {
            TaskDao taskDao = new TaskDaoImpl(connection);

            Task taskToAdd = new Task("Finish lessons", false);
            long id = taskDao.addTask(taskToAdd);
            System.out.println("New entity created with ID: " + id);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------");

            Task addedTask = taskDao.getTask(id);
            System.out.println(addedTask);

            Task nonExistentTask = taskDao.getTask(999);
            System.out.println(nonExistentTask);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------");
            TaskRepository taskRepository = new TaskRepositoryImpl(taskDao);

            Task taskToUpdate = new Task(id, "", true);
            taskRepository.update(taskToUpdate);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------");

            var tasks = taskDao.getAll();

            for (Task task: tasks) {
                System.out.println(task);
            }

            System.out.println("-----------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------");

            taskDao.deleteTask(id);
            System.out.println(taskDao.getTask(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}