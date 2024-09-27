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
import com.blog_rest_app.exception.CustomAuthException;
import com.blog_rest_app.exception.ResourceNotFoundException;
import com.blog_rest_app.repository.CategoryRepository;
import com.blog_rest_app.repository.CommentRepository;
import com.blog_rest_app.repository.PostRepository;
import com.blog_rest_app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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
        User currentUser = getCurrentUser();
        Category category = categoryRepository.findByName(postDTO.category());

        tempPost.setTitle(postDTO.title());
        tempPost.setContent(postDTO.content());

        tempPost.setUser(currentUser);
        tempPost.setDateTime(LocalDateTime.now());

        tempPost.setCategory(category);
        postRepository.save(tempPost);
        return postDTO;

    }

    @Override
    public UpdatePostDTO update(UpdatePostDTO postDTO) {
        Post tempPost = postRepository.findById(postDTO.id())
                .orElseThrow(() -> new ResourceNotFoundException("Post with id: " + postDTO.id() + " not found"));
        if (isUserAuthorized(tempPost.getUser())) {
            tempPost.setTitle(postDTO.title());
            tempPost.setContent(postDTO.content());
            Category category = categoryRepository.findByName(postDTO.category());
            tempPost.setCategory(category);
            postRepository.save(tempPost);
            return postDTO;
        } else throw new CustomAuthException("You do not have perrmision to update this data.");

    }

    private User getCurrentUser() {
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        String finalEmail = email;
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email:" + finalEmail + " not found"));
    }

    @Override
    public void deleteById(int id) {
        Post tempPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id: " + id + " not found"));

        if (isUserAuthorized(tempPost.getUser())) {
            postRepository.deleteById(id);
        } else throw new CustomAuthException("You do not have perrmision to update this data.");

    }

    @Override
    public CreateCommentDTO createComment(CreateCommentDTO commentDTO, int postId) {


        User tempUser = getCurrentUser();
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
        if (isUserAuthorized(tempComment.getUser())) {
            tempComment.setContent(commentDTO.content());
            commentRepository.save(tempComment);
            return commentDTO;
        } else throw new CustomAuthException("You do not have perrmision to update this data.");
    }



    @Override
    public void deleteCommentById(int id) {
        Comment tempComment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment with id: " + id + " not found"));
        if (isUserAuthorized(tempComment.getUser())) {
            commentRepository.deleteById(id);
        } else throw new CustomAuthException("You do not have perrmision to update this data.");
    }

    private boolean isUserAuthorized(User user){
        return user.equals(getCurrentUser()) || isCurrentUserAdmin();
    }

    private boolean isCurrentUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

    private CommentDTO commentToDTO(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getContent(), comment.getUser().getFullName());
    }

    private PostDTO postToDTO(Post post) {
        List<CommentDTO> commentDTOList = post.getComments().stream().map(this::commentToDTO).toList();
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getDateTime(), post.getCategory().getName(), commentDTOList, post.getUser().getFullName());
    }

}
