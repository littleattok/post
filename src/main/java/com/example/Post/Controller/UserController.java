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
}
