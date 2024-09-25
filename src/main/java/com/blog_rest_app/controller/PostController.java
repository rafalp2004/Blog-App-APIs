package com.blog_rest_app.controller;

import com.blog_rest_app.dto.comment.CreateCommentDTO;
import com.blog_rest_app.dto.comment.UpdateCommentDTO;
import com.blog_rest_app.dto.post.CreatePostDTO;
import com.blog_rest_app.dto.post.PostDTO;
import com.blog_rest_app.dto.post.UpdatePostDTO;
import com.blog_rest_app.service.PostService;
import jakarta.validation.Valid;
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
    public CreatePostDTO createPost(@Valid @RequestBody CreatePostDTO postDTO) {
        return postService.save(postDTO);
    }

    @DeleteMapping(path = "/posts/{id}")
    public void deleteById(@PathVariable int id) {
        postService.deleteById(id);
    }

    @PutMapping(path="/posts")
    public UpdatePostDTO updatePost(@Valid @RequestBody UpdatePostDTO postDTO){
        return postService.update(postDTO);

    }

    @PostMapping(path = "/posts/{postId}/comments")
    public CreateCommentDTO createComment(@Valid @RequestBody CreateCommentDTO commentDTO, @PathVariable int postId){
        postService.createComment(commentDTO, postId);
        return commentDTO;
    }
    @PutMapping(path = "/posts/{postId}/comments")
    public UpdateCommentDTO updateComment(@RequestBody UpdateCommentDTO commentDTO){
        postService.updateComment(commentDTO);
        return commentDTO;
    }
    @DeleteMapping(path = "/posts/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable int commentId){
        postService.deleteCommentById(commentId);

    }

}
