package com.example.venusblog.repository;

import com.example.venusblog.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {

}