package com.example.Post.Controller;

import com.example.Post.dto.UserSignUpRequestDto;
import com.example.Post.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import com.example.Post.dto.LoginRequestDto;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserSignUpRequestDto requestDto){
        Long userId = userService.signUp(requestDto);
        return ResponseEntity.created(URI.create("/api/users/"+userId)).build();
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        String token = userService.login(requestDto);
        response.setHeader("Authorization", token);
        return ResponseEntity.ok("로그인 성공");
    }
}
