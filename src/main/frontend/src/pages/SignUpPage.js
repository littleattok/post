import React from 'react';
import {useNavigate} from 'react-router-dom';
import styled from 'styled-components';
import {signUp} from '../api/apiService';

import {useState} from 'react';
const FormContainer = styled.div`
    display: flex;
    flex-direction: column;
    gap: 15px;
    max-width: 400px;
    margin:40px auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    `;

const Input = styled.input`
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 1rem;
`;


function SignUpPage(){

    const navigate =useNavigate();
    const [formData, setFormData] = useState({
        username:'',
        password:'',
        nickname:'',
    });

    const handleChange = (e) =>{
        const {name, value} =e.target;
        setFormData(prevState =>({
            ...prevState,
            [name]:value
        }));
    };


    const handleSubmit = async (e) =>{
        e.preventDefault();
        try{
            await signUp(formData);
            alert("회원가입 완료");
            navigate('/login');
        }
        catch(error){
            console.log("회원가입 실패", error);
            alert("회원가입에 실패했습니다. " + error.message);
        }
    }
    return (
         <div>
            <h1>회원가입</h1>
            <FormContainer onSubmit ={handleSubmit}>
                <Input type = "text" name= "username" value={formData.username} onChange={handleChange} placeholder = "사용자ID" required/>
                <Input type = "password" name= "password" value={formData.password} onChange={handleChange} placeholder = "비밀번호" required/>
                <Input type = "text" name= "nickname" value={formData.nickname} onChange={handleChange} placeholder = "닉네임" required/>
            </FormContainer>
        </div>
    )
}

export default SignUpPage;
