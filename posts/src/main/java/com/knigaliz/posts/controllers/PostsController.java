package com.knigaliz.posts.controllers;

import com.knigaliz.posts.entities.UserEntity;
import com.knigaliz.posts.services.PostService;
import com.knigaliz.posts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class PostsController {
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostsController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
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
}
