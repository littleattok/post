package com.example.Post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF(Cross-Site Request Forgery) 보호를 비활성화합니다.
                // REST API는 상태를 저장하지 않으므로(stateless) CSRF 보호가 필요 없는 경우가 많습니다.
                .csrf(csrf -> csrf.disable()) // 이 부분을 추가합니다.
                .authorizeHttpRequests(authorize -> authorize
                        // "/api/**" 패턴의 모든 요청을 인증 없이 허용합니다.
                        .requestMatchers("/api/**").permitAll()
                        // 그 외의 모든 요청은 인증을 요구합니다.
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}
