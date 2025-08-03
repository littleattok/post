package com.example.Post.Service;



import com.example.Post.domain.Posts;
import com.example.Post.dto.CreatePostRequest;
import com.example.Post.dto.DeletePostRequest;
import com.example.Post.dto.PostResponse;
import com.example.Post.dto.UpdatePostRequest;
import com.example.Post.repository.PostRepository;
import com.example.Post.repository.UserRepository;

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
    private final UserRepository userRepository;
    

   @Transactional
   public Long createdAt(PostCreatedRequestDto requestDto, Stirng ipAddress, String username){
        User user = null;
        if(username!=null){
            user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.");)
        }

        Posts post = Posts.builder()
            .content(reqeustDto.getContent())
            .user(user)
            .authorNickname(user == null ? requestDto.getAuthorNickname() : null )
            .password(user == null ? passwordEncoder.encode(requestDto.getPassword()) : null)
            .ipAddress(user == null ? ipAddress : null)
            .build();

        Posts savedPost = postRepository.save(post);
        return savedPost.getId();
   }

   public List<PostResponseDto> getAllPosts(){

    List<Posts> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));

    return posts.stream()
        .map(PostResponseDto::new)
        .collect(Collector.ToList());
   }




}
