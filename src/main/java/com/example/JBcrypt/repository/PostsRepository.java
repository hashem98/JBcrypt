package com.example.JBcrypt.repository;

import com.example.JBcrypt.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}