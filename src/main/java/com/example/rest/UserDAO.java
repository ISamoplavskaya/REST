package com.example.rest;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final UserDAO INSTANCE = new UserDAO();
    private final List<User> userList = new ArrayList<>();

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    private UserDAO() {
    }

    public void saveUser(User user) {
        userList.add(user);
    }

    public User getById(int id) {
        return userList.stream().filter(user -> user.getId() == id).findAny().orElseThrow();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }

    public void deleteById(int id) {
        User foundUser = userList.stream().filter(user -> user.getId() == id).findAny().orElseThrow();
        userList.remove(foundUser);
    }
}
