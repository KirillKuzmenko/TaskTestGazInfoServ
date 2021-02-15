package service;

import model.User;

public interface UserService {
    User getUserByName(String name);

    void editLastName(User user);
}
