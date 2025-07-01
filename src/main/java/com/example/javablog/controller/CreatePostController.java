package com.example.javablog.controller;

import com.example.javablog.model.User;
import com.example.javablog.model.Post;
import com.example.javablog.service.PostService;
import com.example.javablog.service.PostServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CreatePostController extends HttpServlet {
    private static final Logger accessLogger = LogManager.getLogger("com.example.javablog.logs.access");
    private PostService postService = new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            accessLogger.warn("Unauthorized post creation attempt");
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("user", user);
        req.setAttribute("title", "Create New Post");
        accessLogger.info("User accessed create post page: " + user.getUsername());
        req.getRequestDispatcher("/views/create-post.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            accessLogger.warn("Unauthorized post creation attempt");
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            req.setAttribute("error", "Title and content are required.");
            req.setAttribute("user", user);
            req.setAttribute("title", "Create New Post");
            req.getRequestDispatcher("/views/create-post.jsp").forward(req, res);
            return;
        }

        if (title.length() > 255) {
            req.setAttribute("error", "Title cannot exceed 255 characters.");
            req.setAttribute("user", user);
            req.setAttribute("title", "Create New Post");
            req.getRequestDispatcher("/views/create-post.jsp").forward(req, res);
            return;
        }

        try {
            Post post = new Post();
            post.setUserId(user.getId());
            post.setTitle(title);
            post.setContent(content);
            postService.createPost(post);
            accessLogger.info("Post created by user: " + user.getUsername());
            res.sendRedirect(req.getContextPath() + "/dashboard");
        } catch (Exception e) {
            accessLogger.error("Error creating post for user: " + user.getUsername(), e);
            req.setAttribute("error", "Failed to create post: " + e.getMessage());
            req.setAttribute("user", user);
            req.setAttribute("title", "Create New Post");
            req.getRequestDispatcher("/views/create-post.jsp").forward(req, res);
        }
    }
}