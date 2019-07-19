package info.sjd.dao;

import info.sjd.model.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private static List<User> users = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        User testUser = new User("A_Test", "I_Test", "test_login", "123456");
        users.add(UserDAO.create(testUser));
    }

    @AfterAll
    static void tearDown() {
        users.stream().forEach( user -> UserDAO.delete(user.getId()));
    }

    @Test
    void create() {
        User user = new User("A_Test", "I_Test", "test_login2", "123456");
        User testUser = UserDAO.create(user);

        assertNotNull(testUser);
        assertNotNull(user.getId());
        assertEquals(user.getLogin(), testUser.getLogin());

        users.add(testUser);
    }

    @Test
    void findAll() {
        List<User> testUsers = UserDAO.findAll();

        assertNotNull(testUsers);
        assertTrue(testUsers.size() > 0);
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}