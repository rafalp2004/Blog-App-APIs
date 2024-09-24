package com.blog_rest_app.controller;

import com.blog_rest_app.dto.post.CreatePostDTO;
import com.blog_rest_app.dto.post.PostDTO;
import com.blog_rest_app.dto.post.UpdatePostDTO;
import com.blog_rest_app.entity.Post;
import com.blog_rest_app.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts")
    public List<PostDTO> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping(path = "/posts/{id}")
    public PostDTO getPostById(@PathVariable int id) {
        return postService.findById(id);
    }

    @PostMapping(path = "/posts")
    public CreatePostDTO createPost(@RequestBody CreatePostDTO postDTO) {
        return postService.save(postDTO);
    }

    @DeleteMapping(path = "/posts/{id}")
    public void deleteById(@PathVariable int id) {
        postService.deleteById(id);
    }

    @PutMapping(path="/posts")
    public UpdatePostDTO updatePost(@RequestBody UpdatePostDTO postDTO){
        return postService.update(postDTO);

    }

}
