package com.blog_rest_app.controller;

import com.blog_rest_app.entity.Post;
import com.blog_rest_app.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }
}
