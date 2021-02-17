package service;

import dao.UserDao;
import model.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    UserDao userDao;
    UserService userService;

    @Test
    void getUserByName() {
        userDao = mock(UserDao.class);
        userService = new UserServiceImpl(userDao);

        User user = new User(1L, "kirill", "kirill");
        when(userDao.getUserByName("kirill")).thenReturn(new User(1L, "kirill", "kirill"));

        assertEquals(userService.getUserByName("kirill"), user);
        verify(userDao, times(1)).getUserByName(any());
        verifyNoMoreInteractions(userDao);
    }

    @Test
    void editLastName() {

        userDao = mock(UserDao.class);
        userService = new UserServiceImpl(userDao);


    }
}