package com.example.javablog.controller;

import com.example.javablog.model.User;
import com.example.javablog.service.UserService;
import com.example.javablog.service.UserServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private static final Logger accessLogger = LogManager.getLogger("com.example.javablog.logs.access");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        accessLogger.info("Testing logger in doPost"); // Test log
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Email and password are required.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, res);
            return;
        }

        User user = userService.authenticate(email, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            res.sendRedirect("/dashboard");
        } else {
            req.setAttribute("error", "Invalid email or password.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, res);
        }
        accessLogger.info("Login attempt from: " + email);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        accessLogger.info(String.format("Access %s form ip %s", req.getRequestURL().toString(), req.getRemoteAddr().toString() )); // Test log
        req.getRequestDispatcher("/views/login.jsp").forward(req, res);
    }
}