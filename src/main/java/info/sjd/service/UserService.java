package info.sjd.service;

import info.sjd.dao.UserDAO;
import info.sjd.model.User;

public class UserService {

    public static User create(User user){
        if (UserDAO.findByLogin(user.getLogin()) == null){
            return UserDAO.create(user);
        }
        return null;
    }

    public static User findByLogin(String login){
        return UserDAO.findByLogin(login);
    }

    public static User findUserByLoginAndPassword(String login, String password){
        return UserDAO.findByLoginAndPassword(login, password);
    }

    public static void delete(Integer userId){
        UserDAO.delete(userId);
    }

}
