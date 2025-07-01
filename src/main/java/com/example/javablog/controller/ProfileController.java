package com.example.javablog.controller;

import com.example.javablog.model.User;
import com.example.javablog.service.UserService;
import com.example.javablog.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfileController extends HttpServlet {
    private static final Logger accessLogger = LogManager.getLogger("com.example.javablog.logs.access");
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            accessLogger.warn("Unauthorized profile access attempt");
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("user", user);
        req.setAttribute("title", "Edit Profile");
        accessLogger.info("User accessed profile: " + user.getUsername());
        req.getRequestDispatcher("/views/profile.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            accessLogger.warn("Unauthorized profile update attempt");
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String bio = req.getParameter("bio");
        if (bio != null && bio.length() > 255) {
            req.setAttribute("error", "Bio cannot exceed 255 characters.");
            req.setAttribute("user", user);
            req.setAttribute("title", "Edit Profile");
            req.getRequestDispatcher("/views/profile.jsp").forward(req, res);
            return;
        }

        try {
            user.setBio(bio);
            userService.updateUser(user);
            session.setAttribute("user", user); // Update session
            req.setAttribute("success", "Profile updated successfully.");
            accessLogger.info("Profile updated for user: " + user.getUsername());
        } catch (Exception e) {
            accessLogger.error("Error updating profile for user: " + user.getUsername(), e);
            req.setAttribute("error", "Failed to update profile: " + e.getMessage());
        }

        req.setAttribute("user", user);
        req.setAttribute("title", "Edit Profile");
        req.getRequestDispatcher("/views/profile.jsp").forward(req, res);
    }
}