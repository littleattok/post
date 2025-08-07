package com.example.Post.Controller;

import com.example.Post.dto.PostCreateRequestDto;
import com.example.Post.dto.PostResponseDto;
import com.example.Post.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController{
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostCreateRequestDto requestDto,
                                            HttpServletRequest httpServletRequest,
                                            Principal principal){
        String ipAddress = httpServletRequest.getRemoteAddr();
        String userName = (principal != null) ? principal.getName() : null;

        Long postId = postService.createPost(requestDto, ipAddress, userName);
        return ResponseEntity.created(URI.create("/api/posts/" + postId)).build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        List <PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    //수정 삭제 부분 추가 예정
}