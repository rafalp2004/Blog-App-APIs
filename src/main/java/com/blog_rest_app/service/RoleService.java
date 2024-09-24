package com.blog_rest_app.service;

import com.blog_rest_app.entity.Post;
import com.blog_rest_app.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findByName(String name);

}
