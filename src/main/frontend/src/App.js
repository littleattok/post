import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import GlobalStyle from './styles/GlobalStyle';
import MainLayout from './layout/MainLayout';
import HomePage from './pages/HomePage';
import PostDetailPage from './pages/PostDetailPage';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';


function App(){
    return (
        <GlobalStyle/>
        <Router>
            <Routes>
                <Route path = '/' element={<mainLayout/>}>
                
                </Route>
            </Routes>
        </Router>
    )
}