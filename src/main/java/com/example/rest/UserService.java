package com.example.rest;

import java.util.List;

public class UserService {
    private final UserDAO userDAO = UserDAO.getInstance();

    public void save(User user) {
        userDAO.saveUser(user);
    }

    public User getUser(int id) {
        return userDAO.getById(id);
    }

    public List<User> getAll() {
        return userDAO.getAllUsers();
    }

    public void delete(int id) {
        userDAO.deleteById(id);
    }
}
