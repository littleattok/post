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


    @Lob
    @Column(nullable = false)
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")// 외래키 user_id
    private User user;          // 회원인 경우 이 필드에 저장됨

    private String authorNickname;


    private String ipAddress;


    @Column(nullable = false, length = 60, name="password_hashed")
    private String password;


    @Builder
    public Posts(String content, User user, String authorNickname, String password, String ipAddress){
        this.content = content;
        this.user = user;
        this.authorNickname = authorNickname;
        this.password = password;
        this.ipAddress = ipAddress;
    }
    public void update(String author, String content) {
        this.content = content;
    }
}
