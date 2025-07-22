import axios from 'axios';

const apiClient = axios.create({
    baseURL: '/api',
    // ✅ 수정: 'header' -> 'headers'
    headers: {
        'Content-Type': 'application/json',
    },
});

const getPosts = () => apiClient.get('/posts');
const createPost = (postData) => apiClient.post('/posts', postData);

// ✅ 수정: 백틱(`) 사용 및 인자 이름 통일 (updateData -> postData)
const updatePost = (postId, postData) => apiClient.put(`/posts/${postId}`, postData);

// ✅ 수정: 백틱(`) 사용 및 passwordData를 인자로 받도록 추가
const deletePost = (postId, passwordData) => apiClient.delete(`/posts/${postId}`, { data: passwordData });

export { getPosts, createPost, updatePost, deletePost };