package com.blog_rest_app.service;

import com.blog_rest_app.entity.Post;
import com.blog_rest_app.entity.User;
import com.blog_rest_app.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(int id) {
        return null;
    }

    @Override
    public Post save(Post user) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
