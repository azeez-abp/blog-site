package com.example.javablog.service;

import com.example.javablog.dao.PostDAO;
import com.example.javablog.dao.PostDAOImpl;
import com.example.javablog.model.Post;

public class PostServiceImpl implements PostService {
    private PostDAO postDAO = new PostDAOImpl();

    public void createPost(Post post) {
        postDAO.createPost(post);
    }
}