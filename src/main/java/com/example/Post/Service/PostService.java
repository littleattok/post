package com.example.Post.Service;



import com.example.Post.domain.Posts;
import com.example.Post.dto.CreatePostRequest;
import com.example.Post.dto.DeletePostRequest;
import com.example.Post.dto.PostResponse;
import com.example.Post.dto.UpdatePostRequest;
import com.example.Post.repository.PostRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly= true)
public class PostService {
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    public PostService(PostRepository postRepository, PasswordEncoder passwordEncoder){
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long createPost(CreatePostRequest request){
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Posts post = Posts.builder()
                .author(request.getAuthor())
                .content(request.getContent())
                .password(hashedPassword)
                .build();
        Posts savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    public List<PostResponse> getAllPosts(){
        return postRepository.findAll().stream()
                .map(PostResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request){
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. Id:" + postId));

        //1. 요청으로 받은 평문 비밀번호와 DB에 저장된 해시 비밀번호를 비교
        if (!passwordEncoder.matches(request.getPassword(), post.getPassword())) {

            throw new SecurityException("비밀번호가 일치하지 않습니다.");
        }

        //2. 비밀번호가 일치하면 수정
        post.update(request.getAuthor(), request.getContent());
    }

    @Transactional
    public void deletePost(Long postId, DeletePostRequest request){
        Posts post= postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. Id:" + postId));

        if(!passwordEncoder.matches(request.getPassword(), post.getPassword())) {
            throw new SecurityException("비밀번호가 일치하지 않습니다.");
        }

        postRepository.delete(post);
    }





}
