package com.example.javablog.service;

import com.example.javablog.model.User;

public interface UserService {
    void register(User user);
    boolean emailExists(String email);
}
