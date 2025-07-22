import React, { useState } from 'react';
import DeleteConfirmationModal from './DeleteConfirmationModal';

function PostCard({post, onDelete}){
    const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);

    const handleDeleteSuccess  = () =>{
        setDeleteModalOpen(false);
        onDelete();    
    };

    return (
        <div className="post-card">
            <h3>{post.author}</h3>
            <p>{new Date(post.createdAt).toLocaleString()}</p>
            <h4>{post.title}</h4>
            <p>{post.content}</p>

            {/* 삭제 버튼을 누르면 모달이 열립니다. */}
        <button onClick={() => setDeleteModalOpen(true)}>삭제</button>
        
        {/* 삭제 확인 모달 */}
        <DeleteConfirmationModal
            isOpen={isDeleteModalOpen}
            onClose={() => setDeleteModalOpen(false)}
            // 👇 위에서 정의한 handleDeleteSuccess 함수를 연결합니다.
            onDeleteSuccess={handleDeleteSuccess}
            postId={post.id}
        />
    </div>
    )
}
export default PostCard;