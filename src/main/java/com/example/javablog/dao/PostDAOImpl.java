package com.example.javablog.dao;

import com.example.javablog.model.Post;
import com.example.javablog.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostDAOImpl implements PostDAO {
    private static final Logger logger = LogManager.getLogger(PostDAOImpl.class);

    public void createPost(Post post) {
        String sql = "INSERT INTO posts (user_id, title, content) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, post.getUserId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getContent());
            stmt.executeUpdate();
            logger.info("Post created for user_id: " + post.getUserId());
        } catch (SQLException e) {
            logger.error("Error creating post for user_id: " + post.getUserId(), e);
            throw new RuntimeException("Failed to create post", e);
        }
    }
}