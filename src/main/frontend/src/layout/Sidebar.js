import React from 'react';
import {Link} from 'react-router-dom';
import styled from 'styled-components';

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
    return (
        <SidebarContainer>
            <UserProfileContainer>
                <h2>내 정보</h2>
                {/*추후 추가할 로그인/비로그인 시 다른 UI*/}
            </UserProfileContainer>
            <NavList>
                <NavItem>
                    <Link to="/">홈</Link>
                </NavItem>
                <NavItem>
                    <Link to="/login">로그인</Link>
                </NavItem>
                <NavItem>
                    <Link to="/signup">회원가입</Link>
                </NavItem>
            </NavList>
        </SidebarContainer>
    )
}

export default Sidebar;
