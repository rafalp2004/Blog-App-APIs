package com.blog_rest_app.service;

import com.blog_rest_app.dto.comment.CommentDTO;
import com.blog_rest_app.dto.comment.CreateCommentDTO;
import com.blog_rest_app.dto.comment.UpdateCommentDTO;
import com.blog_rest_app.dto.post.CreatePostDTO;
import com.blog_rest_app.dto.post.PostDTO;
import com.blog_rest_app.dto.post.UpdatePostDTO;
import com.blog_rest_app.entity.Category;
import com.blog_rest_app.entity.Comment;
import com.blog_rest_app.entity.Post;
import com.blog_rest_app.entity.User;
import com.blog_rest_app.exception.ResourceNotFoundException;
import com.blog_rest_app.repository.CategoryRepository;
import com.blog_rest_app.repository.CommentRepository;
import com.blog_rest_app.repository.PostRepository;
import com.blog_rest_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public List<PostDTO> findAll() {
        return postRepository.findAll().stream().map(this::postToDTO).toList();
    }

    @Override
    public PostDTO findById(int id) {
        Post tempPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id: " + id + " not found"));
        return postToDTO(tempPost);

    }


    @Override
    public CreatePostDTO save(CreatePostDTO postDTO) {
        Post tempPost = new Post();
        tempPost.setTitle(postDTO.title());
        tempPost.setContent(postDTO.content());
        //Later it will be getting User from session.
        int userId = 4;
        tempPost.setUser(userRepository.findById(4).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found")));
        tempPost.setDateTime(LocalDateTime.now());
        Category category = categoryRepository.findByName(postDTO.category());
        tempPost.setCategory(category);
        postRepository.save(tempPost);
        return postDTO;

    }

    @Override
    public UpdatePostDTO update(UpdatePostDTO userDTO) {
        Post tempPost = postRepository.findById(userDTO.id()).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userDTO.id() + " not found"));
        tempPost.setTitle(userDTO.title());
        tempPost.setContent(userDTO.content());
        Category category = categoryRepository.findByName(userDTO.category());
        tempPost.setCategory(category);
        postRepository.save(tempPost);
        return userDTO;
    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public CreateCommentDTO createComment(CreateCommentDTO commentDTO, int postId) {


        //Later it will be getting User from session.
        int userId = 4;
        User tempUser = userRepository.findById(4).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found"));
        Post tempPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id: " + postId + " not found"));


        Comment tempComment = new Comment();
        tempComment.setPost(tempPost);
        tempComment.setUser(tempUser);
        tempComment.setContent(commentDTO.content());
        tempPost.addComment(tempComment);
        postRepository.save(tempPost);
        return commentDTO;
    }

    @Override
    public UpdateCommentDTO updateComment(UpdateCommentDTO commentDTO) {

        Comment tempComment = commentRepository.findById(commentDTO.id()).orElseThrow(() -> new ResourceNotFoundException("Comment with id: " + commentDTO.id() + " not found"));
        tempComment.setContent(commentDTO.content());
        commentRepository.save(tempComment);
        return commentDTO;
    }

    @Override
    public void deleteCommentById(int id) {
        commentRepository.deleteById(id);
    }


    private CommentDTO commentToDTO(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getContent(), comment.getUser().getFullName());
    }

    private PostDTO postToDTO(Post post) {

        List<CommentDTO> commentDTOList = post.getComments().stream().map(this::commentToDTO).toList();
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getDateTime(), post.getCategory().getName(), commentDTOList, post.getUser().getFullName());

    }

}
