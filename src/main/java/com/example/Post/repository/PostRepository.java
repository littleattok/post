package com.example.Post.repository;

import com.example.Post.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Posts, Long> {

}
