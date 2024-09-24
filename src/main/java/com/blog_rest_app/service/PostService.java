package com.blog_rest_app.service;

import com.blog_rest_app.entity.Post;
import com.blog_rest_app.entity.User;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    Post findById(int id);

    Post save(Post user);

    void deleteById(int id);
}
