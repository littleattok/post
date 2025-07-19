package com.example.Post.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name="posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable= false, length =32)
    private String author;

    @Lob
    @Column(nullable = false)
    private String content;


    @Column(nullable = false, length = 60, name="password_hashed")
    private String password;


    @Builder
    public Posts(String author, String content, String password){
        this.author = author;
        this.content = content;
        this.password = password;
    }

    public void update(String author, String content) {
        this.author = author;
        this.content = content;
    }
}
