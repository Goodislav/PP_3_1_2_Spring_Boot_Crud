package ru.goodislav.springboot.dao;



import ru.goodislav.springboot.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> getAllUsers();
    User showUser(Long id);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
}
