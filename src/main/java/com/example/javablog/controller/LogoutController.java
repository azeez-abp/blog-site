package com.example.javablog.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LogoutController extends HttpServlet {
    private static final Logger accessLogger = LogManager.getLogger("com.example.javablog.logs.access");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Don't create a new session
        if (session != null) {
            String username = (session.getAttribute("user") != null) ? 
                session.getAttribute("user").toString() : "unknown";
            accessLogger.info("User logged out: " + username);
            session.invalidate(); // Invalidate the session
        }
        res.sendRedirect(req.getContextPath() + "/login"); // Redirect to login page
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res); // Handle POST the same as GET
    }
}