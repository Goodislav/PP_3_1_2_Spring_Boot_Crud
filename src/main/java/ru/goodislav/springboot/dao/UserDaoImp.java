package ru.goodislav.springboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.goodislav.springboot.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User showUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(Long id, User user) {
        User updateUser = entityManager.find(User.class, id);
        if (updateUser != null) {
            updateUser.setName(user.getName());
            updateUser.setLastname(user.getLastname());
            updateUser.setAge(user.getAge());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            entityManager.merge(updateUser);
        }
    }

    @Override
    public void deleteUser(Long id) {
        User deleteUser = entityManager.find(User.class, id);
        if (deleteUser != null) {
            entityManager.remove(deleteUser);
        }
    }
}
