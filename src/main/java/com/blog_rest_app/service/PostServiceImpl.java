package com.blog_rest_app.service;

import com.blog_rest_app.dto.comment.CommentDTO;
import com.blog_rest_app.dto.post.CreatePostDTO;
import com.blog_rest_app.dto.post.PostDTO;
import com.blog_rest_app.dto.post.UpdatePostDTO;
import com.blog_rest_app.dto.user.CreateUserDTO;
import com.blog_rest_app.dto.user.UpdateUserDTO;
import com.blog_rest_app.entity.Category;
import com.blog_rest_app.entity.Comment;
import com.blog_rest_app.entity.Post;
import com.blog_rest_app.entity.User;
import com.blog_rest_app.repository.CategoryRepository;
import com.blog_rest_app.repository.PostRepository;
import com.blog_rest_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<PostDTO> findAll() {
        return postRepository.findAll().stream().map(s -> postToDTO(s)).toList();
    }

    @Override
    public PostDTO findById(int id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post tempPost = optionalPost.get();
            return postToDTO(tempPost);

        }
        return null;
    }


    @Override
    public CreatePostDTO save(CreatePostDTO postDTO) {
        Post tempPost = new Post();
        tempPost.setTitle(postDTO.title());
        tempPost.setContent(postDTO.content());
        //Later it will be getting User from session.
        tempPost.setUser(userRepository.findById(4).get());
        tempPost.setDateTime(LocalDateTime.now());
        Category category = categoryRepository.findByName(postDTO.category());
        tempPost.setCategory(category);
        postRepository.save(tempPost);
        return postDTO;


    }

    @Override
    public UpdatePostDTO update(UpdatePostDTO userDTO) {
        Post tempPost = postRepository.findById(userDTO.id()).get();
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


    private CommentDTO commentToDTO(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getContent(), comment.getUser().getFullName());
    }

    private PostDTO postToDTO(Post post) {

        List<CommentDTO> commentDTOList = post.getComments().stream().map(s -> commentToDTO(s)).toList();
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getDateTime(), post.getCategory().getName(), commentDTOList, post.getUser().getFullName());

    }

}
