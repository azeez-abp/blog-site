package com.example.javablog.dao;

import com.example.javablog.model.User;

public interface UserDAO {
    void registerUser(User user);
    User getUserByEmail(String email);
}
