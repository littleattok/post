import React from 'react';
import {Outlet} from 'react-router-dom';
import styled from 'styled-components';
import Sidebar from './Sidebar';

const LayoutContainer = styled.div`
    display: flex;
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    `;

const MainContent = styled.main`
    flex-grow:1;
    margin-left:20px;
    `;

function MainLayout(){
    return (
        <LayoutContainer>
            <Sidebar/>
            <MainContent>
                <Outlet/>   {/* 여기에 페이지 등이 렌더링 될것*/}
            </MainContent>
        </LayoutContainer>
    )
}

export default MainLayout;