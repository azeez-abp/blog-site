package com.example.javablog.dao;

import com.example.javablog.model.User;
import com.example.javablog.util.DBUtil;
import com.example.javablog.model.Post;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class/*class where log is comming from*/);
    public void registerUser(User user) {
        String sql = "INSERT INTO users (username, email, password, bio, avatar_url) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getBio());
            stmt.setString(5, user.getAvatarUrl());
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error("Error registering user: " + user.getUsername(), e);
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
         logger.error("Error retrieving user by email: " + email);
        try (
             Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
            ) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBio(rs.getString("bio"));
                user.setAvatarUrl(rs.getString("avatar_url"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error retrieving user by email UNknow messsgaw: " + email);
            logger.error("Error retrieving user by email: " + email, e);
        }
        return null;
    }
   /**
    *  try () {

        }catch{
            
        }
    */
    public User getUserById(int id) {
        String sqlStm = "SELECT * FROM users WHERE id = ?";
        try (
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlStm)
        ) {
            stmt.setInt(1, id);
             ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBio(rs.getString("bio"));
                user.setAvatarUrl(rs.getString("avatar_url"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                return user;
            }

        }catch (Exception e) {
           e.printStackTrace();
        }

        return null;
    }public void updateUser(User user) {
        String sql = "UPDATE users SET avatar_url = ?, bio = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getAvatarUrl());
            stmt.setString(2, user.getBio());
            stmt.setInt(3, user.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Updated profile for user: " + user.getUsername());
            } else {
                logger.warn("No user found with id: " + user.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating user: " + user.getUsername(), e);
        }
    }

    public List<Post> getUserPosts(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setUserId(rs.getInt("user_id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCoverImageUrl(rs.getString("cover_image_url"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                posts.add(post);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving posts for user_id: " + userId, e);
        }
        return posts;
    }





}
