package ru.goodislav.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goodislav.springboot.dao.UserDao;
import ru.goodislav.springboot.model.User;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.showUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user.getId(), user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
