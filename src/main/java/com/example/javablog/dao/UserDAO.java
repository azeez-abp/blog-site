package com.example.javablog.dao;

import com.example.javablog.model.User;
import java.util.List;
import com.example.javablog.model.Post;


public interface UserDAO {
    void registerUser(User user);
    User getUserByEmail(String email);

    void updateUser(User user);
    List<Post> getUserPosts(int userId);
}
