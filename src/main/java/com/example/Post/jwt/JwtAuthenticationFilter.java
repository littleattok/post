package com.example.Post.jwt;

import com.example.Post.security.UserDetailsServiceImpl;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final jwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{
        String token = jwtUtil.getJwtFromHeader(request);
        if(token != null){
            if(jwtUtil.validateToken(token)){
                Claims userInfo = jwtUtil.getUserInfoFromToken(token);
                String username = userInfo.getSubject();

                // SecurityContext에 인증 정보 설정
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                Authentication authentiacation = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                context.setAuthentication(authentiacation);
                SecurityContextHolder.setContext(context);

            }
        }
        filterChain.doFilter(request,response);
    }
}
