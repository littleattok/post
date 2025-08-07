package com.example.Post.dto;

import com.example.Post.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private final Long id;
    private final String content;
    private final String authorNickname;
    private final LocalDateTime createdAt;
    private final String ipAddress;
    private final String profileImageUrl;

    public PostResponseDto(Posts post){
        this.id = post.getId();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();

        if(post.getUser()!=null){
            this.authorNickname = post.getUser().getNickname();
            this.profileImageUrl = post.getUser().getProfileImageUrl();
            this.ipAddress = null;
        }
        else{
            this.authorNickname = post.getAuthorNickname();
            this.profileImageUrl = null;
            this.ipAddress = maskIpAddress(post.getIpAddress());
        }
    }


    private String maskIpAddress(String ip){
        if(ip ==null || ip.isEmpty()){
            return "";
        }

        String [] parts = ip.split("\\.");
        if(parts.length== 4){
            return parts[0]+ "." + parts[1]+".***.***";
        }
        return ip;
    }
}
 