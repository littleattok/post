import React,{useState} from 'react';
import {createPost} from '../api';

function PostForm({ onPostCreated }) {
   const [formData, setFormData] = useState({
       author: '', 
       content: '',
       password: ''
   });

    const handleChange = (e) =>{
        const {name,value}= e.target;
        setFormData(prevState =>({
            ...prevState,
            [name]:value
        }));
    };

    const handleSubmit  = async (e) =>{
        e.preventDefault();
        try{
            await createPost(formData);

            setFormData({author:'',content:'',password:''});
            if(onPostCreated){
                onPostCreated();
            }

        } catch(error){
            console.error('게시글 작성에 실패하였습니다.',error);
        }
    };
    return (
        <form onSubmit = {handleSubmit}>
            <input
                type="text"
                name="author"
                value={formData.author}
                onChange={handleChange}
                placeholder="이름"
                required/>

            <input  
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                placeholder="비밀번호"
                required/> 

            <textarea
                name="content"
                value={formData.content}
                onChange={handleChange}
                placeholder="내용"
                required/>

            <button type="submit">작성</button>
             
        </form>
    );
}

export default PostForm;
