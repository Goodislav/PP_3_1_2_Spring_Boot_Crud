package ru.goodislav.springboot.service;



import ru.goodislav.springboot.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void updateUser(User user);
    void deleteUser(Long id);
}
