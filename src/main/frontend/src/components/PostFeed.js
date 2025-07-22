import React, {useState,useEffect}  from 'react';
import {getPosts} from '../api';
import PostForm from './PostForm';
import PostCard from './PostCard';

function PostFeed(){

    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchPosts = async () =>{
        try{
            setLoading(true);
            const response = await getPosts();
            setPosts(response.data.sort((a,b)=>new Date(b.createdAt)- new Date(a.createdAt)));
        }catch(error){
            console.error('게시글을 불러오는 데 실패했습니다.', error);
        }finally{
            setLoading(false);
        }
    };

    useEffect(()=>{
        fetchPosts();
    });

    if(loading){
        return <div>로딩 중...</div>;
    }

    return (
        <div>
            <PostForm onPostCreated={fetchPosts} />
            <div className="post-list">
                {posts.map(post => (
                    <PostCard key={post.id} post={post} onPostDeleted={fetchPosts} />
                ))}
            </div>
        </div>
    )
}

export default PostFeed;