import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import PostForm from './components/PostForm'; // PostForm 컴포넌트를 가져옵니다.
import PostFeed from './components/PostFeed'; // 우리가 만든 PostFeed 컴포넌트를 가져옵니다.
function App() {
    return (
        <Router>
            <nav>
                <Link to="/">홈</Link>
                <Link to="/new-post">새 글 작성</Link>
            </nav>
            <main>
                <Routes>
                   {/* ✅ 수정: PostFeed에 넘겨주던 모든 props를 제거합니다. */}
                    <Route path="/" element={<PostFeed />} />

                    {/* 참고: 현재 PostForm은 PostFeed안에 포함되어 있어 이 경로는 사용되지 않습니다.
                      만약 글 작성 페이지를 완전히 분리하고 싶다면 구조를 변경해야 합니다.
                    */}
                    {/* <Route path="/new-post" element={<PostForm />} /> */}
                </Routes>
            </main>
        </Router>
    );
}

export default App;
