package com.example.Post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreatePostRequest {


    private String author;
    private String content;
    private String password;



}
