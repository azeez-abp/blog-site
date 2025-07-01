package com.example.javablog.service;

import com.example.javablog.model.User;
import com.example.javablog.model.Post;
import java.util.List;

public interface UserService {
    void register(User user);
    boolean emailExists(String email);
    User authenticate(String email, String password);

    void updateUser(User user);
    List<Post> getUserPosts(int userId); //
}
