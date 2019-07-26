package info.sjd.dao;

import info.sjd.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public  static User create(User user){

        String sql = "INSERT INTO users(login, password, first_name, last_name) " +
                "VALUES(?,?,?,?)";

        try (Connection connection = PSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());

            preparedStatement.executeUpdate();

            User createdUser = findByLogin(user.getLogin());
            if (createdUser != null){
                user.setId(createdUser.getId());
            } else{
                return null;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public static User findByLogin(String login) {
        User user = null;

        String sql = "SELECT * FROM users WHERE login=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                user = new User();
                setUserParams(user, resultSet);
                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public static User findByLoginAndPassword(String login, String password) {
        User user = null;

        String sql = "SELECT * FROM users WHERE login=? AND password=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                user = new User();
                setUserParams(user, resultSet);
                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public static List<User> findAll() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                setUserParams(user, resultSet);
                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public static User findById(Integer id) {
        User user = null;

        String sql = "SELECT * FROM users WHERE id=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                user = new User();
                setUserParams(user, resultSet);
                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public static User update(User user) {
        User updatedUser = null;

        String sql = "UPDATE users SET login=?, password=?, first_name=?, last_name=? WHERE id=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setInt(5, user.getId());

            preparedStatement.executeUpdate();

            updatedUser = new User();
            updatedUser.clone(user);

        } catch (SQLException e){
            e.printStackTrace();
        }

        return updatedUser;
    }

    public static void delete(Integer id) {

        String sql = "DELETE FROM users WHERE id=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void setUserParams(User user, ResultSet resultSet) {
        try {
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
