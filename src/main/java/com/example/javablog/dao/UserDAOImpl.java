package com.example.javablog.dao;

import com.example.javablog.model.User;
import com.example.javablog.util.DBUtil;
import java.sql.*;

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
    }


}
