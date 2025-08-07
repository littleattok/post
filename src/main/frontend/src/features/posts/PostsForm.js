import React, {useState} from 'react';
import styled from 'styled-components';
import {createPost} from '../../api/posts';

const FormWrapper  = styled.form`
    display: flex;
    flex-direction: column;
    gap:10px;
    margin: 20px 0;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
`;

const TextArea = styled.textarea`
    width: 100%;
    min-height: 100px;
    padding:10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 1rem;
    resize: vertical;
`;

const Input = styled.input`
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 1rem;
`;

function PostForm({onPostCreated}){
    const[ formData,setFormData] = useState({
        content:'',
        authorNickname:'',
        password:''
    });

    const handleChange = (e) => {
        const {name, value} =e.target;
        setFormData(prevState =>({
            ...prevState,
            [name]:value
        }));
    };

    const handleSubmit = async (e) =>{
        e.preventDefault();
        if(!formData.authorNickname || !formData.content || !formData.password){
            alert("모든 필드를 입력해주세요.");
            return;
        }
        try{
            await createPost(formData);
            setFormData({
                content:'', authorNickname:'', password:''
            });
            if(onPostCreated){
                onPostCreated();
            }
        }
        catch(error){
            console.error("게시글 작성 실패", error);
            alert("게시글 작성에 실패했습니다. " + error.message);
        }
    };

    return(
        <FormWrapper onSubmit = {handleSubmit}>
            <TextArea name ="content" value={formData.content} onChange= {handleChange} placeholder="어떤 일이 있으신가요" required/>
            <Input type = "text" name = "authorNickname" value = {formData.authorNickname} onChange = {handleChange} placeholder= "닉네임" required/>
            <Input type = "password" name = "password" value = {formData.password} onChange = {handleChange} placeholder = "비밀번호" required />
            <button type= "submit">게시글 작성</button>
        </FormWrapper> 
    )
}

export default PostForm;