import React from 'react';
import {useNavigate} from 'react-router-dom';
import styled from 'styled-components';
import {login} from '../api/apiService';
import useAuthstore from '../store/authStore';
import {useState} from 'react';

const FormContainer = styled.form`
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

function LoginPage(){

    const navigate = useNavigate();
    const {setToken} = useAuthstore();
    const [credentials, setCredentials] = useState({username:'', password: ''});

    const handleChange = (e) =>{
        const {name, value} = e.target;
        setCredentials(prevState => ({...prevState, [name]: value}));
    }

    const handleSubmit = async (e) =>{
        e.preventDefault();

        try{
            const response = await login(credentials);
            const token = response.headers.authorization;
            if(token){
                setToken(token);        //토큰 저장
                alert("로그인 성공");
                navigate('/');          //홈으로 이동
            }
        }
        catch(error){
            console.error("로그인 실패", error);
            alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.');
        }
    };

    
    return (
        <div>
            <h1>로그인</h1>
            <FormContainer onSubmit={handleSubmit}>
                <Input type="text" name="username" value={credentials.username} onChange={handleChange} placeholder="사용자 ID" required />
                <Input type="password" name="password" value={credentials.password} onChange={handleChange} placeholder="비밀번호" required />
                <button type="submit">로그인</button>
            </FormContainer>
        </div>
    )
}

export default LoginPage;
