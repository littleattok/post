package com.example.Post.Service;

import com.example.Post.domain.User;
import com.example.Post.repository.UserRepository;
import com.example.Post.dto.UserSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp (UserSignUpRequestDto requestDto){
        if(userRepository.findByUsername(requestDto.getUsername()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .nickname(requestDto.getNickname())
                .build();
                
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
