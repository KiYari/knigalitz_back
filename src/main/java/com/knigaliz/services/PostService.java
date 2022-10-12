package com.knigaliz.services;

import com.knigaliz.models.Post;
import com.knigaliz.models.User;
import com.knigaliz.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findByUser(User user) {
        return postRepository.findByUser(user);
    }

    public Post findById(int id) {
        Optional<Post> post =  postRepository.findById(id);
        return post.orElse(null);
    }


}
