import axios from 'axios';

const apiClient = axios.create({
    baseURL: '/api',
    headers: {
        'Content-Type': 'application/json',
    },
});

// 사용자 관련 API
export const signUp = (userData) => apiClient.post('/users/signup', userData);
export const login = (credentials) => apiClient.post('/users/login', credentials);

// 게시글 관련 API
export const getAllPosts = () => apiClient.get('/posts');
export const createPost = (postData) => apiClient.post('/posts',postData);



export default apiClient;