package com.cursojava.demo.dao;

import com.cursojava.demo.model.User;

public interface AuthDao {
    User getUserforCredentials(User user);
}
