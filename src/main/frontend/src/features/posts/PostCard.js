import React from 'react';
import styled from 'styled-conponents';

const CardContainer = styled.div`
    background-color: #ffffff;
    border:1px solid #000000;
    border-radius:8px;
    padding: 15px;
    margin-bottom:15px
`;

const CardHeader = styled.div`
    display:flex;
    align-items:center;
    margin-bottom:10px;
`;

const AuthorNickname = styled.span`
    font-weight:bold;
    margin-right:10px;
`;

const IpAddress = styled.span`
    font-size:0.9rem;
    color: #657786;
`;

const Content = styled.p`
    font-size: 1rem;
    line-height: 1.5;
    white-space: pre-wrap; /* 줄바꿈 유지 */
`;

const TimeStamp = styled.span`
    color: #657786;
    font-size: 0.8rem;
    margin-top: 10px;
`;


function PostCard({post}) {
    return (
        <CardContainer>
            <CardHeader>
                <AuthorNickname>{post.authorNickname}</AuthorNickname>
                {post.ipAddress && <IpAddress>({post.ipAddress})</IpAddress>}
            </CardHeader>
            <Content>{post.content}</Content>
            <TimeStamp>{new Date(post.createdAt).toLocaleString()}</TimeStamp>
        </CardContainer>
    )
}

export default PostCard;