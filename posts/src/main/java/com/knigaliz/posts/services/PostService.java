package com.knigaliz.posts.services;

import com.knigaliz.posts.entities.Post;
import com.knigaliz.posts.repositories.PostRepository;
import com.knigaliz.posts.util.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(int id) {
        return postRepository.findById(id).orElseThrow(() ->
                new PostNotFoundException("There is no post with this id"));
    }

    public Post findByTitle(String title) {
        return postRepository.findByTitle(title).orElseThrow(() ->
                new PostNotFoundException("No post with such title"));
    }
}
