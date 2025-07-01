package com.example.javablog.controller;

import com.example.javablog.model.User;
import com.example.javablog.service.UserService;
import com.example.javablog.service.UserServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

@MultipartConfig(
    maxFileSize = -1, // Set in code from .env
    maxRequestSize = 10485760, // 10MB
    fileSizeThreshold = 1024 * 1024 // 1MB
)
public class FileUploadController extends HttpServlet {
    private static final Logger accessLogger = LogManager.getLogger("com.example.javablog.logs.access");
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String UPLOAD_DIR;
    private static final long MAX_FILE_SIZE;
    private static final String[] ALLOWED_FILE_TYPES;

    static {
        UPLOAD_DIR = dotenv.get("UPLOAD_DIR", "uploads");
        MAX_FILE_SIZE = Long.parseLong(dotenv.get("MAX_FILE_SIZE", "5242880"));
        ALLOWED_FILE_TYPES = dotenv.get("ALLOWED_FILE_TYPES", "image/jpeg,image/png").split(",");
    }

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            accessLogger.warn("Unauthorized file upload attempt");
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Get the file part
        Part filePart = req.getPart("avatar");
        if (filePart == null || filePart.getSize() == 0) {
            req.setAttribute("error", "No file selected.");
            req.getRequestDispatcher("/views/profile.jsp").forward(req, res);
            return;
        }

        // Validate file size
        if (filePart.getSize() > MAX_FILE_SIZE) {
            req.setAttribute("error", "File size exceeds limit of " + (MAX_FILE_SIZE / (1024 * 1024)) + "MB.");
            accessLogger.warn("File size exceeded for user: " + user.getUsername());
            req.getRequestDispatcher("/views/profile.jsp").forward(req, res);
            return;
        }

        // Validate file type
        String contentType = filePart.getContentType();
        if (!Arrays.asList(ALLOWED_FILE_TYPES).contains(contentType)) {
            req.setAttribute("error", "Invalid file type. Only JPEG and PNG are allowed.");
            accessLogger.warn("Invalid file type uploaded by user: " + user.getUsername() + ", type: " + contentType);
            req.getRequestDispatcher("/views/profile.jsp").forward(req, res);
            return;
        }

        // Generate unique filename
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        String filePath = UPLOAD_DIR + File.separator + uniqueFileName;

        // Ensure upload directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
            uploadDir.setWritable(true, false);
        }

        // Save the file
        try {
            filePart.write(filePath);
            accessLogger.info("File uploaded by user: " + user.getUsername() + ", path: " + filePath);

            // Update user's avatar_url in database
            user.setAvatarUrl(filePath);
            userService.updateUser(user);

            // Update session
            session.setAttribute("user", user);
            req.setAttribute("success", "Avatar uploaded successfully.");
        } catch (Exception e) {
            accessLogger.error("Error uploading file for user: " + user.getUsername(), e);
            e.printStackTrace();
            req.setAttribute("error", "Failed to upload file: " + e.getMessage());
        }

        req.getRequestDispatcher("/views/profile.jsp").forward(req, res);
    }
}

  
