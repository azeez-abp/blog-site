package com.example.javablog.service;

import com.example.javablog.dao.UserDAO;
import com.example.javablog.dao.UserDAOImpl;
import com.example.javablog.model.User;
import org.mindrot.jbcrypt.BCrypt;

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
}
