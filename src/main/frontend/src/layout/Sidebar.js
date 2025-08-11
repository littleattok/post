import React from 'react';
import {Link, useNavigate} from 'react-router-dom';
import styled from 'styled-components';


import useAuthStore from '../store/authStore';

import {jwtDecode} from 'jwt-decode';
const SidebarContainer = styled.div`
    width: 280px;
    flex-shrink:0;
    padding: 20px;
    background-color: #ffffff;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    height: fit-content;
`;

const NavList =styled.ul`
    list-style: none;
    margin-top: 0;
    `;

const NavItem = styled.li`
    margin-bottom: 15px;
    font-size: 1.2rem;
    font-weight: bold;

    a {
        color: #050505;
        &:hover{
            color: #1877f2;
        }
    }
`;
const UserProfileContainer = styled.div`
  /* 4단계에서 구현 */
  margin-bottom: 20px;
`;

function Sidebar(){


    const {isAuthenticated, token, logout} = useAuthStore();
    const navigate = useNavigate(); 

    let username = null;
    if(token){
        try{
            username = jwtDecode(token).sub;
        }
        catch(e){
            console.error("Invalid token", e);
        }
    }

    const handleLogout = () =>{
        logout();
        alert("로그아웃 되었습니다.");
        navigate("/login");
    };



    return (
        <SidebarContainer>
            <UserProfileContainer>
                <h2>내 정보</h2>
                {isAuthenticated && username ?(
                    <div>
                        <p>{username}님 환영합니다.</p>
                        <button onClick={handleLogout}>로그아웃</button>
                    </div>
                ):(
                    <div>
                        <p>로그인 후 이용해주세요.</p>
                    </div>
                )}
            </UserProfileContainer>
            <NavList>
                <NavItem><Link to="/">홈</Link></NavItem>
                {!isAuthenticated && (
                    <>
                        <NavItem><Link to="/login">로그인</Link></NavItem>
                        <NavItem><Link to="/signup">회원가입</Link></NavItem>
                    </>
                )}
            </NavList>
        </SidebarContainer>
    )
}

export default Sidebar;
