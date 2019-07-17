package info.sjd.dao;

import info.sjd.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public  static User create(User user){

        String sql = "INSERT INTO users(login, password, first_name, last_name) " +
                "VALUES(?,?,?,?)";

        try {

            Connection connection = PSQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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

    private static User findByLogin(String login) {
        User user = null;

        String sql = "SELECT * FROM users WHERE login=?";

        try (
                Connection connection = PSQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, user.getLogin());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));

                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    // SELECT * WHERE id=?
    // UPDATE users SET login=?, password=?, first_name=?, last_name=? WHERE id=?
    // DELETE FROM users WHERE id=?
}
