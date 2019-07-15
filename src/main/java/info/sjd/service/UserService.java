package info.sjd.service;

import info.sjd.model.User;

public class UserService {

    public static User findUserByLoginAndPassword(String login, String password){
        User user = new User("Alex", "Ignatenko", "ignatenko2207", "123456");

        if(user.getLogin().equals(login) && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
