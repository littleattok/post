package com.example.Post.repository;

import com.example.Post.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
    
//DB에서 조회하기 위한 레포지토리
