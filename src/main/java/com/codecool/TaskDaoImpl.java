package com.codecool;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TaskDaoImpl implements TaskDao {
    private Connection connection;

    @Override
    @SneakyThrows
    public long addTask(Task task) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.ADD_TASK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setBoolean(2, task.isFinished());
            preparedStatement.executeUpdate();

            //|Id|
            //|13|
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        }

        return 0L;
    }

    @Override
    @SneakyThrows
    public Task getTask(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_TASK)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String description = resultSet.getString("description");
                    boolean finished = resultSet.getBoolean("finished");

                    return new Task(id, description, finished);
                }
            }
        }

        return null;
    }

    @SneakyThrows
    @Override
    public List<Task> getAll() {
        List<Task> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Queries.GET_ALL_TASKS)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                boolean finished = resultSet.getBoolean("finished");

                resultList.add(new Task(id, description, finished));
            }
        }

        return resultList;
    }

    @SneakyThrows
    @Override
    public void updateTask(Task task) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_TASK)) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setBoolean(2, task.isFinished());
            preparedStatement.setLong(3, task.getId());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public void deleteTask(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.DELETE_TASK)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
