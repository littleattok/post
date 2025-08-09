package com.example.Post.jwt;

import io.jsonwebsocket.*;
import io.jsonwebsocket.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotataion.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class jwtUtil {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final long TOKEN_TIME = 1000L * 60 * 60 ; // 1시간

    @Value("${jwt.secret.key}")
    public String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init(){
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShakeKey(bytes);
    }


    public String CreateToken(String username){
        Date date = new Date();
        return BEARER_PREFIX + Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(date.getTime()+ TOKEN_TIME))
                .setIssuedAt(date)
                .signWWith(key, signatureAlgorithm)
                .compact();
    }

    public String getJwtFromHeader (HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token){
        try{
            Jwts.parseBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch(Exception e){
            //로그 처리 필요함
        }
        return false;

    }


    public Claims getUserInfoFromToken(String token){
        return Jwts.parseBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
