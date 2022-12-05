package com.knigaliz.posts.repositories;

import com.knigaliz.posts.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByTitle(String title);
    Optional<Post> findById(int id);
}
