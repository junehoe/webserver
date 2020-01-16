package webserver.database;

import webserver.todo.TodoItem;
import webserver.todo.TodoList;

import java.sql.*;

public class DatabaseHandler {
    private Connection connection;
    private final String ID = "id";
    private final String TITLE = "title";
    private final String COMPLETE = "complete";

    public DatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    public int insertTodoItem(String value) {
        int id = 0;
        try {
            String query = String.format("INSERT INTO TODO (TITLE) VALUES ('%s');", value);
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void populate(TodoList todoList) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM TODO ORDER BY ID ASC;");

        while (results.next()) {
            TodoItem todoItem = new TodoItem(
                    results.getInt(ID),
                    results.getString(TITLE),
                    results.getBoolean(COMPLETE));
            todoList.add(todoItem);
        }
    }

    public void toggleTodoItemStatus(TodoItem item) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM TODO WHERE ID = " + item.getId() + ";");

            if (result.next()) {
                boolean isComplete = result.getBoolean(COMPLETE);
                String query = String.format("UPDATE TODO set COMPLETE = %s where ID = %d;",
                        !isComplete, item.getId());
                statement.executeUpdate(query);
                item.setStatus(!isComplete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTodoItem(int id, String title) {
        try {
            String query = String.format("UPDATE TODO set TITLE = '%s' where ID = %d", title, id);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTodoItem(int id) {
        try {
            String query = String.format("DELETE FROM TODO where ID = %d", id);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
