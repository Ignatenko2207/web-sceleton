package info.sjd.dao;

import info.sjd.model.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private static List<User> users = new ArrayList<>();

    private static Logger logger = Logger.getLogger(UserDAOTest.class.getName());

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
        if (!users.isEmpty()){
            User testUser = UserDAO.findById(users.get(0).getId());
            assertNotNull(testUser);
            assertNotNull(testUser.getId());
            assertNotNull(testUser.getLogin());
        } else {
            logger.info("User list is empty!");
            fail("Test cannot be completed");
        }
    }

    @Test
    void update() {
        if (!users.isEmpty()){
            User changedUser = new User();
            changedUser.clone(users.get(0));
            changedUser.setLogin("new_login");
            assertTrue( users.get(0).getLogin() != changedUser.getLogin() );

            User testUser = UserDAO.update(changedUser);

            assertNotNull(testUser);
            assertEquals(testUser.getLogin(), changedUser.getLogin());
            assertTrue(testUser.getId() == changedUser.getId() && testUser.getId() == users.get(0).getId());
        } else {
            logger.info("User list is empty!");
            fail("Test cannot be completed");
        }

    }
}