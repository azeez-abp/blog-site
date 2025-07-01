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
import java.util.Collections;

public class DashboardController extends HttpServlet {
    private static final Logger accessLogger = LogManager.getLogger("com.example.javablog.logs.access");
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            accessLogger.warn("Unauthorized dashboard access attempt");
            res.sendRedirect("/login");
            return;
        }
        User user = (User) session.getAttribute("user");
        try {
            req.setAttribute("user", user);
            req.setAttribute("posts", userService.getUserPosts(user.getId()) != null ? userService.getUserPosts(user.getId()) : Collections.emptyList());
            req.setAttribute("title", "Dashboard");
            accessLogger.info("User accessed dashboard: " + user.getUsername());
            req.getRequestDispatcher("/views/dashboard.jsp").forward(req, res);
        } catch (Exception e) {
           
            e.printStackTrace();
            accessLogger.error("Error forwarding to dashboard for user: " + user.getUsername(), e);
            req.setAttribute("error", "An error occurred while loading the dashboard: " + e.getMessage());
            //req.getRequestDispatcher("/views/error.jsp").forward(req, res);
        }
    }
}