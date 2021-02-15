package dao;

import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoJdbcTest {

    @Test
    public void getUserByNameTest() {

        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();

        User user = new User(1L,"kirill", "kuzmenko");

        User testUser = userDaoJdbc.getUserByName("kirill");

        assertEquals(testUser, user);
    }

    @Test
    public void editLastNameTest() {

        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();

        User user = userDaoJdbc.getUserByName("kirill");

        user.setLastName("kirill");

        userDaoJdbc.editLastName(user);

        assertEquals(user, userDaoJdbc.getUserByName(user.getName()));
    }
}