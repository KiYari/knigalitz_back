package com.knigaliz.posts.controllers;

import com.knigaliz.posts.dto.PostDto;
import com.knigaliz.posts.entities.Post;
import com.knigaliz.posts.entities.UserEntity;
import com.knigaliz.posts.services.PostService;
import com.knigaliz.posts.services.UserService;
import com.knigaliz.posts.util.exceptions.PostNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
public class PostsController {
    private final UserService userService;
    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public PostsController(UserService userService, PostService postService, ModelMapper modelMapper) {
        this.userService = userService;
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/posts")
    public String posts(Principal principal) {
        UserEntity user = userService.findByLogin(principal.getName());
        System.out.println("I'm working: " + principal.getName());
        System.out.println(user.toString());
        return user.toString();
    }

    @GetMapping("/post/{id}")
    public String home(@PathVariable int id) {
        return postService.findById(id).toString();
    }

    @GetMapping("/post/title/{title}")
    public String titlefind(@PathVariable String title) {
        return postService.findByTitle(title).toString();
    }

    @PostMapping(value = "/posts/create", consumes = "application/json")
    public @ResponseBody Map<String, String> createPost(@RequestBody PostDto postDto, Principal principal) {
        try {
            System.out.println(postDto.toString());
            Post post = postService.createAndRetrivePost(dtoToPost(postDto), principal.getName());
            System.out.println(post.toString());

            return Map.of("post", post.toString());
        } catch (Exception e) {
            throw new PostNotCreatedException("Something went wrong when creating a user");
        }
    }

    @PostMapping(value = "posts/edit/{id}", consumes = "application/json")
    public @ResponseBody Map<String, String> editPost(@RequestBody PostDto postDto,
                                                      @PathVariable int id) {
        try {
            Post post = postService.updateAndRetrivePost(dtoToPost(postDto), id);

            return Map.of("post", post.toString());
        } catch (Exception e) {
            throw new PostNotCreatedException("Something went wrong when updating a post");
        }
    }

    @PostMapping("posts/delete/{id}")
    public @ResponseBody Map<String, String> editPost(@PathVariable int id) {
        try {
            postService.deletePost(id);
            return Map.of("deleted", String.valueOf(id));
        } catch (Exception e) {
            throw new PostNotCreatedException("Something went wrong when updating a post");
        }
    }

    public Post dtoToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}
