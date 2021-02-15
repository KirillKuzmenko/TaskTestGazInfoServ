package service;

import dao.UserDao;
import dao.UserDaoJdbc;
import model.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoJdbc();
    }
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public void editLastName(User user) {
        userDao.editLastName(user);
    }
}
