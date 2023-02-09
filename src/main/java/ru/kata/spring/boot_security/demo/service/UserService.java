package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserInfo(int id);
    User findUserByUsername (String login);

    void updateUser(User user);
    void deleteUser(int id);
    void  addUser(User user);

}
