import axios from 'axios';

const apiClient = axios.create({
    baseURL: '/api',
    headers: {
        'Content-Type': 'application/json',
    },
});

const getPosts = () => apiClient.get('/posts');
const createPost = (postData) => apiClient.post('/posts', postData);

const updatePost = (postId, postData) => apiClient.put(`/posts/${postId}`, postData);

const deletePost = (postId, passwordData) => apiClient.delete(`/posts/${postId}`, { data: passwordData });

export { getPosts, createPost, updatePost, deletePost };