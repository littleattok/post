package com.example.Post.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserSignUpRequestDto{

    @NotBlank(message = "사용자 아이디는 필수 항목입니다.")
    @Size(min=4, max=20, message="사용자 아이디는 4~20자 이내여야합니다.")
    private String username;


    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min=8, message="비밀번호는 8자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 항목입니다.")
    @Size(min=2, max=20, message="닉네임은 2~20자 이내여야 합니다.")
    private String nickname;
    
}