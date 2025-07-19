package com.example.Post.dto;

import com.example.Post.domain.Posts;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private final Long id;
    private final String author;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private PostResponse(Long id, String author, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PostResponse from (Posts post) {
        return new PostResponse(
                post.getId(),
                post.getAuthor(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
