import React from 'react';
import styled from 'styled-components';
import {getAllPosts} from '../api/posts';

import {PostForm} from '../features/Posts/PostForm';
import {PostCard} from '../features/Posts/PostCard';

const HomePageContainer = styled.div`
    width: 100%;
    `;


function HomePage(){

    const [posts, setPosts]= useState([]);
    const [loading, setLoading] = useState(true);

    const fetchPosts = useCallback(async () =>{
        try{
            setLoading(true);
            const response = await getAllPosts();
            setPosts(response.data);
        } catch (error) {
            console.error("게시글을 불러오는 데 실패했습니다.", error);
        } finally {
            setLoading(false);
        }},[]);

    useEffect(()=>{
        fetchPosts();
    },[fetchPosts]);
    if(loading){
        return <div>불러오는 중...</div>;
    }

    
    return (
        <HomePageContainer>
            <h1>전체 게시글 피드</h1>
                <PostForm onPostCreated={fetchPosts}/>
                <div>
                    {posts.map(post =>(
                        <PostCard key={post.id} post ={post}/>
                    ))}
                </div>
        </HomePageContainer>
    );
}

export default HomePage;