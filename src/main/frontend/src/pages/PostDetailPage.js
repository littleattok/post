import React from 'react';

import {useParams} from 'react-router-dom';

function PostDetailPage(){
    const {id} = useParams(); // URL에서 게시글 ID를 가져옴

    return (
        <div>
            <h1>게시글 상세 페이지(ID:{id})</h1>
            {/*여기에 추후 게시글 상세 컴포넌트 배치 예정*/}
        </div>
    )
}
export default PostDetailPage;