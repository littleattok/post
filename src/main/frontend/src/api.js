import axios from 'axios';


const apiClient = axios.create({
    baseURL: '/api',
    header: {
        'content-type':'application/json',

    },
})


const getPosts = () =>apiClient.get('/posts');
const createPost = (postData) => apiClient.post('/posts',postData);
const updatePost = (postId,postData)=> apiClient.put('/posts/${postId}',updateData);
const deletePost = (postId) => apiClient.delete(`/posts/${postId}`,{data:passwordData});