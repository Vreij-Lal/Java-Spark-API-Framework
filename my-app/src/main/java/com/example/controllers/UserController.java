package com.example.controllers;

import com.example.dao.UserDao;
import com.example.models.User;

public class UserController {
    private final UserDao userDao;

    public UserController() {
        this.userDao = new UserDao();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public boolean createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userDao.createUser(user);
    }
}
