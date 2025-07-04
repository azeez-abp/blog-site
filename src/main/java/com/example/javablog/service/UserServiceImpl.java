package com.example.javablog.service;

import com.example.javablog.dao.UserDAO;
import com.example.javablog.dao.UserDAOImpl;
import com.example.javablog.model.User;
import org.mindrot.jbcrypt.BCrypt;

import com.example.javablog.model.Post;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    public void register(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDAO.registerUser(user);
    }

    public boolean emailExists(String email) {
        return userDAO.getUserByEmail(email) != null;
    }
    public User authenticate(String email, String password) {
        
        User user = userDAO.getUserByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user; // Authentication successful
        }
        return null; // Authentication failed
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public List<Post> getUserPosts(int userId) {
        return userDAO.getUserPosts(userId);
    }
}
