package com.example.javablog.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * To display uploaded avatars, configure your servlet container (e.g., Tomcat) to serve files from the uploads directory.
 */
public class FileServlet extends HttpServlet {
    private static final String UPLOAD_DIR = System.getenv("UPLOAD_DIR") != null ? System.getenv("UPLOAD_DIR") : "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requestedFile = req.getPathInfo();
        if (requestedFile == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File file = new File(UPLOAD_DIR, requestedFile);
        if (!file.exists() || file.isDirectory()) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Path path = Paths.get(file.getAbsolutePath());
        String contentType = getServletContext().getMimeType(file.getName());
        res.setContentType(contentType != null ? contentType : "application/octet-stream");
        res.setContentLength((int) file.length());
        Files.copy(path, res.getOutputStream());
    }
}