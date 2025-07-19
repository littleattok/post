package com.example.Post.Controller;

import com.example.Post.dto.CreatePostRequest;
import com.example.Post.dto.DeletePostRequest;
import com.example.Post.dto.PostResponse;
import com.example.Post.Service.PostService;
import com.example.Post.dto.UpdatePostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.net.URI;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody CreatePostRequest request){
        Long postId = postService.createPost(request);
        return ResponseEntity.created(URI.create("/api/posts/" + postId)).build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        List<PostResponse> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }


    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId,@RequestBody UpdatePostRequest request){
        postService.updatePost(postId,request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long PostId, @RequestBody DeletePostRequest request){
        postService.deletePost(PostId, request);
        return ResponseEntity.noContent().build();
    }
}
