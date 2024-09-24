package com.blog_rest_app.service;

import com.blog_rest_app.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User save(User user);

    void deleteById(int id);
}
