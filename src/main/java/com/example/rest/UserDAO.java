package com.example.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class UserDAO {
    private static UserDAO INSTANCE;
    private final List<User> userList;
    private final AtomicLong idGenerator;

    private UserDAO() {
        userList = new ArrayList<>();
        userList.add(new User("Alex Dog",25));
        userList.add(new User("Vlad Shevchuk",35));
        userList.add(new User("Oleg Babukh",26));
        idGenerator=new AtomicLong(userList.size()+1);
    }

    public static synchronized UserDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDAO();
        }
        return INSTANCE;
    }

    public void saveUser(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        userList.add(user);
    }

    public User getById(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }

    public void deleteById(Long id) {
        User foundUser = userList.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        userList.remove(foundUser);
    }
}
