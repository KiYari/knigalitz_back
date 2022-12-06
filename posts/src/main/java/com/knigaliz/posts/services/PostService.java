package com.knigaliz.posts.services;

import com.knigaliz.posts.entities.Post;
import com.knigaliz.posts.repositories.PostRepository;
import com.knigaliz.posts.util.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post findById(int id) {
        return postRepository.findById(id).orElseThrow(() ->
                new PostNotFoundException("There is no post with this id"));
    }

    public Post findByTitle(String title) {
        return postRepository.findByTitle(title).orElseThrow(() ->
                new PostNotFoundException("No post with such title"));
    }

    public Post createAndRetrivePost(Post post, String login) {
        post.setUser(userService.findByLogin(login));
        postRepository.save(post);
        return post;
    }

    public Post updateAndRetrivePost(Post post, int id) {
        Post post1 = findById(id);
        post1.setTitle(post.getTitle());
        post1.setDescription(post.getDescription());
        postRepository.save(post1);
        return post1;
    }

    public void deletePost(int id) {
        postRepository.delete(findById(id));
    }
}
