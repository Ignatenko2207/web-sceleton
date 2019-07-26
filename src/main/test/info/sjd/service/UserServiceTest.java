package info.sjd.service;

import info.sjd.dao.UserDAO;
import info.sjd.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private static List<User> users = new ArrayList<>();

    @BeforeAll
    static void setPreData(){
        User user = new User("Alex", "Ignatenko", "test_user", "test_pass");
        user = UserDAO.create(user);
        users.add(user);
    }

    @Test
    void createNewLoginUser(){
        User user = new User("Alex_new", "Ignatenko", "test_user_new", "test_pass");
        UserDAO userDAOFindMock = Mockito.mock(UserDAO.class);
        Mockito.when(userDAOFindMock.findByLogin("test_user_new")).thenReturn(null);
        UserDAO userDAOCreateMock = Mockito.mock(UserDAO.class);
        Mockito.when(userDAOCreateMock.create(user)).thenReturn(user);

        User mockUser = UserService.create(user);
        assertNotNull(mockUser);

        Mockito.verify(userDAOFindMock, Mockito.times(1)).findByLogin("test_user_new");
        Mockito.verify(userDAOCreateMock, Mockito.times(1)).create(user);
    }


    @Test
    void testFindUserByLoginAndPassword (){
        if(!users.isEmpty()){
            User userInDB = UserService.findUserByLoginAndPassword(users.get(0).getLogin(), users.get(0).getPassword());

            assertNotNull(userInDB);
            assertEquals(users.get(0).getFirstName(), userInDB.getFirstName());
        } else{
            fail("Collection users is empty!");
        }
    }

    @AfterAll
    static void deletePreData(){
        users.stream().forEach(user -> UserDAO.delete(user.getId()));
    }

}