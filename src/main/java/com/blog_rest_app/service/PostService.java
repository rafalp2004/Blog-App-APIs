package com.blog_rest_app.service;

import com.blog_rest_app.dto.comment.CreateCommentDTO;
import com.blog_rest_app.dto.comment.UpdateCommentDTO;
import com.blog_rest_app.dto.post.CreatePostDTO;
import com.blog_rest_app.dto.post.PostDTO;
import com.blog_rest_app.dto.post.UpdatePostDTO;
import com.blog_rest_app.dto.user.UpdateUserDTO;
import com.blog_rest_app.entity.Post;
import com.blog_rest_app.entity.User;

import java.util.List;

public interface PostService {
    List<PostDTO> findAll();

    PostDTO findById(int id);

    CreatePostDTO save(CreatePostDTO user);

    UpdatePostDTO update(UpdatePostDTO user);

    void deleteById(int id);

    CreateCommentDTO createComment(CreateCommentDTO commentDTO, int postId);

    UpdateCommentDTO updateComment(UpdateCommentDTO commentDTO);

    void deleteCommentById(int id);

}
