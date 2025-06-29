package com.example.javablog.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;


public class LoginController extends HttpServlet{

 @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, res);
    }

}