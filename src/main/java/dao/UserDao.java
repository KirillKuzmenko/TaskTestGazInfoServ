package dao;

import model.User;

public interface UserDao {

    User getUserByName(String name);

    void editLastName(User user);
}
