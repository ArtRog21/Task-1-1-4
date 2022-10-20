package dao;

import model.User;
import util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS users (id serial PRIMARY KEY NOT NULL, userName VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL)";

            statement.executeUpdate(createTable);
            System.out.println("Таблица создана");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(SQL);
            System.out.println("Таблица удалена");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String userName, String lastName, byte age) {
        try {
            Statement statement = connection.createStatement();
            String saveUser = "INSERT INTO users (userName, lastName, age) VALUES (" + "'" + userName + "', '" + lastName + "'," + age + ")";
            statement.executeUpdate(saveUser);
            System.out.println("Пользователь с именем " + userName + " сохранен");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            String removeUser = "DELETE FROM users WHERE ID = id";
            statement.executeUpdate(removeUser);
            System.out.println("Пользователь с id " + id + " удален");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM users";
            statement.executeUpdate(SQL);
            System.out.println("Данные таблицы удалены");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}