package com.cursojava.demo.dao;

import com.cursojava.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void deleteUser(Long id);

    void register(User user);
}
