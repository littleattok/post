package com.example.Post.dto;

import lombok.Getter;
import lombok.Setter;



//익명에 쓸 것
@Getter
@Setter
public class PostCreateRequestDto {
    
    @NotBlank
    private String content;

    @NotBlank
    private String password;

    @NotBlank
    private String authorNickname;

}
