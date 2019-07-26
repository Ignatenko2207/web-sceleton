package info.sjd.service;

import info.sjd.dao.UserDAO;
import info.sjd.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
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
        UserDAO userDAOMock = mock(UserDAO.class);
        when(userDAOMock.findByLogin("test_user_new")).thenReturn(null);
        when(userDAOMock.create(user)).thenReturn(user);

        User mockUser = UserService.create(user);
        assertNotNull(mockUser);

        verify(userDAOMock, times(1)).findByLogin("test_user_new");
        verify(userDAOMock, times(1)).create(user);
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

    @Test
    void testDeleteUser(){
        UserDAO userDAOMock = mock(UserDAO.class);
        doNothing().when(userDAOMock).delete(anyInt());

        UserService.delete(15);

        verify(userDAOMock, times(1)).delete(anyInt());
    }

    @AfterAll
    static void deletePreData(){
        users.stream().forEach(user -> UserDAO.delete(user.getId()));
    }

}