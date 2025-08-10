package com.example.Post.Service;

import com.example.Post.domain.User;
import com.example.Post.repository.UserRepository;
import com.example.Post.dto.UserSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Post.jwt.jwtUtil;
import com.example.Post.dto.LoginRequestDto;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtUtil jwtUtil;


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

    @Transactional(readOnly = true)
    public String login(LoginRequestDto requestDto){
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.createToken(user.getUsername());
    }
}
