package com.blog_rest_app.controller;

import com.blog_rest_app.dto.comment.CreateCommentDTO;
import com.blog_rest_app.dto.comment.UpdateCommentDTO;
import com.blog_rest_app.dto.post.CreatePostDTO;
import com.blog_rest_app.dto.post.PostDTO;
import com.blog_rest_app.dto.post.UpdatePostDTO;
import com.blog_rest_app.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int id) {
        PostDTO postDTO = postService.findById(id);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<CreatePostDTO> createPost(@Valid @RequestBody CreatePostDTO postDTO) {
        CreatePostDTO createdPost = postService.save(postDTO);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/posts/{postId}")
    public ResponseEntity<Void> deleteById(@PathVariable int postId) {
        postService.deleteById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/posts")
    public ResponseEntity<UpdatePostDTO> updatePost(@Valid @RequestBody UpdatePostDTO postDTO) {
        UpdatePostDTO updatedPost = postService.update(postDTO);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @PostMapping(path = "/posts/{postId}/comments")
    public ResponseEntity<CreateCommentDTO> createComment(@Valid @RequestBody CreateCommentDTO commentDTO, @PathVariable int postId) {
        CreateCommentDTO createdCommentDTO = postService.createComment(commentDTO, postId);
        return new ResponseEntity<>(createdCommentDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/posts/{postId}/comments")
    public ResponseEntity<UpdateCommentDTO>  updateComment(@RequestBody UpdateCommentDTO commentDTO) {
        UpdateCommentDTO updatedCommentDTO = postService.updateComment(commentDTO);
        return new ResponseEntity<>(updatedCommentDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
        postService.deleteCommentById(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
